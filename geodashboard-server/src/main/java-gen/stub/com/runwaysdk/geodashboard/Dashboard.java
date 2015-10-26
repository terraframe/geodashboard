/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package com.runwaysdk.geodashboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.ontology.Term;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generated.system.gis.geo.GeoEntityAllPathsTableQuery;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.geodashboard.gis.persist.AllAggregationType;
import com.runwaysdk.geodashboard.gis.persist.DashboardLayer;
import com.runwaysdk.geodashboard.gis.persist.DashboardMap;
import com.runwaysdk.geodashboard.gis.persist.DashboardMapQuery;
import com.runwaysdk.geodashboard.gis.persist.GeoEntityThematicQueryBuilder;
import com.runwaysdk.geodashboard.gis.persist.GeometryAggregationStrategy;
import com.runwaysdk.geodashboard.gis.persist.GeometryThematicQueryBuilder;
import com.runwaysdk.geodashboard.gis.persist.ThematicQueryBuilder;
import com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition;
import com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery;
import com.runwaysdk.geodashboard.ontology.Classifier;
import com.runwaysdk.geodashboard.ontology.ClassifierAllPathsTableQuery;
import com.runwaysdk.geodashboard.ontology.ClassifierIsARelationship;
import com.runwaysdk.geodashboard.ontology.ClassifierQuery;
import com.runwaysdk.geodashboard.ontology.ClassifierTermAttributeRoot;
import com.runwaysdk.geodashboard.ontology.ClassifierTermAttributeRootQuery;
import com.runwaysdk.geodashboard.report.ReportItemQuery;
import com.runwaysdk.query.AttributeCharacter;
import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.Coalesce;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.RolesQuery;
import com.runwaysdk.system.gis.geo.AllowedIn;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.system.gis.geo.GeoEntityQuery;
import com.runwaysdk.system.gis.geo.GeoNode;
import com.runwaysdk.system.gis.geo.GeoNodeQuery;
import com.runwaysdk.system.gis.geo.Universal;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdClass;

public class Dashboard extends DashboardBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static class MdClassComparator implements Comparator<MdClass>, Reloadable
  {

    @Override
    public int compare(MdClass m1, MdClass m2)
    {
      return m1.getDisplayLabel().getValue().compareTo(m2.getDisplayLabel().getValue());
    }
  }

  private static final long serialVersionUID = 2043512251;

  public Dashboard()
  {
    super();
  }

  public static DashboardQuery getSortedDashboards()
  {
    if (!GeodashboardUser.hasAccess(AccessConstants.ADMIN))
    {
      GeodashboardUser currentUser = GeodashboardUser.getCurrentUser();

      QueryFactory f = new QueryFactory();

      GeodashboardUserQuery userQuery = new GeodashboardUserQuery(f);
      userQuery.WHERE(userQuery.getId().EQ(currentUser.getId()));

      RolesQuery rolesQuery = new RolesQuery(f);
      rolesQuery.WHERE(rolesQuery.singleActor(userQuery));

      DashboardQuery q = new DashboardQuery(f);
      q.WHERE(q.getDashboardRole().EQ(rolesQuery));

      q.ORDER_BY_ASC(q.getDisplayLabel().localize());

      return q;
    }
    else
    {
      QueryFactory f = new QueryFactory();
      DashboardQuery q = new DashboardQuery(f);

      q.ORDER_BY_ASC(q.getDisplayLabel().localize());

      return q;
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    if (this.getRemovable() != null && !this.getRemovable())
    {
      DashboardDeleteException ex = new DashboardDeleteException();
      ex.setLabel(this.getDisplayLabel().getValue());

      throw ex;
    }

    // Delete all saved conditions
    DashboardConditionQuery query = new DashboardConditionQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    OIterator<? extends DashboardCondition> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        DashboardCondition condition = it.next();
        condition.delete();
      }
    }
    finally
    {
      it.close();
    }

    // Delete the corresponding map
    this.getMap().delete();

    // Delete the wrapper mapping
    OIterator<? extends MetadataWrapper> mIterator = this.getAllMetadata();

    try
    {
      while (mIterator.hasNext())
      {
        MetadataWrapper metadata = mIterator.next();
        metadata.delete();
      }
    }
    finally
    {
      mIterator.close();
    }

    Roles role = this.getDashboardRole();

    super.delete();

    // Delete the dashboard role
    role.delete();
  }

  public DashboardMap getMap()
  {
    DashboardMapQuery query = new DashboardMapQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    OIterator<? extends DashboardMap> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public String getMapId()
  {
    DashboardMap map = this.getMap();

    if (map != null)
    {
      return map.getId();
    }

    return null;
  }

  public MdClass[] getSortedTypes()
  {
    // This operation should use only cached objects
    OIterator<? extends MetadataWrapper> iter = this.getAllMetadata();

    List<MdClass> mdClasses = new LinkedList<MdClass>();
    try
    {
      while (iter.hasNext())
      {
        MetadataWrapper mw = iter.next();
        mdClasses.add(mw.getWrappedMdClass());
      }
    }
    finally
    {
      iter.close();
    }

    Collections.sort(mdClasses, new MdClassComparator());

    return mdClasses.toArray(new MdClass[mdClasses.size()]);
  }

  @Transaction
  public static Dashboard create(Dashboard dashboard)
  {
    dashboard.apply();

    return dashboard;
  }

  @Override
  @Transaction
  public void apply()
  {
    boolean isNew = isNew();

    if (this.isNew() && !this.isAppliedToDB())
    {
      String dashboardLabel = this.getDisplayLabel().getValue();
      String roleName = dashboardLabel.replaceAll("\\s", "");

      // Create the Dashboard Role
      Roles role = new Roles();
      role.setRoleName(RoleView.DASHBOARD_NAMESPACE + "." + roleName);
      role.getDisplayLabel().setValue(dashboardLabel);
      role.apply();

      this.setDashboardRole(role);
    }

    super.apply();

    if (isNew)
    {
      DashboardMap map = new DashboardMap();
      map.setName(this.getDisplayLabel().getValue());
      map.setDashboard(this);
      map.apply();

      DashboardState state = new DashboardState();
      state.setDashboard(this);
      state.apply();
    }
  }

  @Override
  @Transaction
  public void applyWithOptions(String[] userIds, String name)
  {
    this.lock();
    this.getDisplayLabel().setValue(name);
    this.apply();
    this.unlock();

    assignUsers(this.getId(), userIds);
  }

  @Override
  @Transaction
  public Dashboard clone(String name)
  {
    Dashboard clone = new Dashboard();
    clone.getDisplayLabel().setDefaultValue(name);
    clone.setName(name);
    clone.setCountry(this.getCountry());
    clone.setRemovable(true);
    clone.apply();

    OIterator<? extends DashboardMetadata> allMetadata = this.getAllMetadataRel();

    try
    {
      while (allMetadata.hasNext())
      {
        DashboardMetadata rel = allMetadata.next();

        MetadataWrapper existingWrapper = rel.getChild();

        MetadataWrapper cloneWrapper = existingWrapper.clone(clone);

        DashboardMetadata dm = clone.addMetadata(cloneWrapper);
        dm.setListOrder(rel.getListOrder());
        dm.apply();
      }
    }
    finally
    {
      allMetadata.close();
    }

    DashboardMap map = clone.getMap();

    // Clone the layer definitions
    DashboardLayer[] layers = this.getMap().getOrderedLayers();

    for (DashboardLayer layer : layers)
    {
      layer.clone(map);
    }

    // Clone the global state
    DashboardState state = DashboardState.getDashboardState(this, null);
    state.clone(this);

    GeodashboardUser user = GeodashboardUser.getCurrentUser();

    if (user != null)
    {
      RoleDAO roleDAO = RoleDAO.get(clone.getDashboardRoleId()).getBusinessDAO();
      roleDAO.assignMember(UserDAO.get(user.getId()));
    }

    return clone;
  }

  public static Dashboard getDashboardByLabel(String label)
  {
    DashboardQuery query = new DashboardQuery(new QueryFactory());
    query.WHERE(query.getDisplayLabel().localize().EQ(label));
    OIterator<? extends Dashboard> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static String[] getTextSuggestions(String mdAttributeId, String text, Integer limit)
  {
    List<String> suggestions = new LinkedList<String>();

    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();
    MdClassDAOIF mdClass = mdAttributeConcrete.definedByClass();

    QueryFactory factory = new QueryFactory();

    BusinessQuery bQuery = factory.businessQuery(mdClass.definesType());
    AttributeCharacter selectable = bQuery.aCharacter(mdAttributeConcrete.definesAttribute());

    ValueQuery query = new ValueQuery(factory);
    query.SELECT_DISTINCT(selectable);
    query.WHERE(selectable.LIKEi("%" + text + "%"));
    query.ORDER_BY_ASC(selectable);
    query.restrictRows(limit, 1);

    OIterator<ValueObject> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ValueObject object = iterator.next();
        String value = object.getValue(mdAttributeConcrete.definesAttribute());

        suggestions.add(value);
      }
    }
    finally
    {
      iterator.close();
    }

    return suggestions.toArray(new String[suggestions.size()]);
  }

  public static final com.runwaysdk.geodashboard.ontology.Classifier[] getClassifierRoots(String mdAttributeId)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();

    QueryFactory factory = new QueryFactory();

    ClassifierTermAttributeRootQuery rootQuery = new ClassifierTermAttributeRootQuery(factory);
    rootQuery.WHERE(rootQuery.getParent().EQ(mdAttributeConcrete.getId()));

    ClassifierQuery classifierQuery = new ClassifierQuery(factory);
    classifierQuery.WHERE(classifierQuery.EQ(rootQuery.getChild()));

    OIterator<? extends Classifier> iterator = classifierQuery.getIterator();

    try
    {
      LinkedList<Classifier> roots = new LinkedList<Classifier>(iterator.getAll());
      return roots.toArray(new Classifier[roots.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public static String getClassifierTree(String mdAttributeId)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();
    ClassifierTermAttributeRootQuery rootQuery = new ClassifierTermAttributeRootQuery(new QueryFactory());

    rootQuery.WHERE(rootQuery.getParent().EQ(mdAttributeConcrete.getId()));

    OIterator<? extends ClassifierTermAttributeRoot> iterator = null;

    try
    {
      iterator = rootQuery.getIterator();

      JSONArray nodes = new JSONArray();

      while (iterator.hasNext())
      {
        ClassifierTermAttributeRoot relationship = iterator.next();
        Classifier classifier = relationship.getChild();

        if (relationship.getSelectable())
        {
          nodes.put(classifier.getJSONObject());
        }
        else
        {
          OIterator<Term> children = null;

          try
          {
            children = classifier.getDirectDescendants(ClassifierIsARelationship.CLASS);

            List<Term> list = children.getAll();

            Collections.sort(list, new TermComparator());

            for (Term term : list)
            {
              Classifier child = (Classifier) term;

              nodes.put(child.getJSONObject());
            }
          }
          finally
          {
            if (children != null)
            {
              children.close();
            }
          }
        }
      }

      return nodes.toString();
    }
    finally
    {
      if (iterator != null)
      {
        iterator.close();
      }
    }
  }

  public static Classifier[] getClassifierSuggestions(String mdAttributeId, String text, Integer limit)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();

    QueryFactory factory = new QueryFactory();
    ClassifierTermAttributeRootQuery rootQuery = new ClassifierTermAttributeRootQuery(factory);
    ClassifierQuery classifierQuery = new ClassifierQuery(factory);
    ClassifierAllPathsTableQuery allPathQuery = new ClassifierAllPathsTableQuery(factory);

    rootQuery.WHERE(rootQuery.getParent().EQ(mdAttributeConcrete.getId()));
    allPathQuery.WHERE(allPathQuery.getParentTerm().EQ(rootQuery.getChild()));

    classifierQuery.WHERE(classifierQuery.EQ(allPathQuery.getChildTerm()));
    classifierQuery.AND(classifierQuery.getDisplayLabel().localize().LIKEi("%" + text + "%"));
    classifierQuery.restrictRows(limit, 1);

    OIterator<? extends Classifier> iterator = classifierQuery.getIterator();

    try
    {
      LinkedList<Classifier> suggestions = new LinkedList<Classifier>(iterator.getAll());
      return suggestions.toArray(new Classifier[suggestions.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public static String[] getCategoryInputSuggestions(String mdAttributeId, String geoNodeId, String universalId, String aggregationVal, String text, Integer limit, DashboardCondition[] conditions)
  {
    Set<String> suggestions = new TreeSet<String>();

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(mdAttributeId);
    String attributeName = mdAttribute.definesAttribute();

    ThematicQueryBuilder builder = getBuilder(geoNodeId, universalId, aggregationVal, conditions, mdAttribute);
    ValueQuery query = builder.getThematicValueQuery();

    OIterator<ValueObject> iterator = null;

    try
    {
      iterator = query.getIterator();

      while (iterator.hasNext())
      {
        ValueObject object = iterator.next();

        String value = object.getValue(attributeName);

        if (value.toLowerCase().contains(text.toLowerCase()))
        {
          suggestions.add(value);
        }
      }
    }
    finally
    {
      if (iterator != null)
      {
        iterator.close();
      }
    }

    String[] array = suggestions.toArray(new String[suggestions.size()]);

    return Arrays.copyOf(array, Math.min(limit, array.length));
  }

  private static ThematicQueryBuilder getBuilder(String geoNodeId, String universalId, String aggregationVal, DashboardCondition[] conditions, MdAttributeDAOIF mdAttribute)
  {
    GeoNode geoNode = GeoNode.get(geoNodeId);
    List<DashboardCondition> conditionList = Arrays.asList(conditions);
    AllAggregationType aggregationType = AllAggregationType.valueOf(aggregationVal);

    if (universalId.equals(GeometryAggregationStrategy.VALUE))
    {
      return new GeometryThematicQueryBuilder(new QueryFactory(), mdAttribute, null, aggregationType, conditionList, geoNode);
    }
    else
    {
      Universal universal = Universal.get(universalId);

      return new GeoEntityThematicQueryBuilder(new QueryFactory(), mdAttribute, null, aggregationType, conditionList, universal, geoNode);
    }
  }

  public static Dashboard[] getDashboardsForCountry(GeoEntity country)
  {
    DashboardQuery query = new DashboardQuery(new QueryFactory());
    query.WHERE(query.getCountry().EQ(country));

    OIterator<? extends Dashboard> it = query.getIterator();

    try
    {
      List<? extends Dashboard> dashboards = it.getAll();

      return dashboards.toArray(new Dashboard[dashboards.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public ValueQuery getGeoEntitySuggestions(String text, Integer limit)
  {
    ValueQuery query = new ValueQuery(new QueryFactory());

    GeoEntity country = this.getCountry();

    GeoEntityQuery entityQuery = new GeoEntityQuery(query);
    GeoEntityAllPathsTableQuery aptQuery = new GeoEntityAllPathsTableQuery(query);

    SelectableChar id = entityQuery.getId();
    Coalesce universalLabel = entityQuery.getUniversal().getDisplayLabel().localize();
    Coalesce geoLabel = entityQuery.getDisplayLabel().localize();
    SelectableChar geoId = entityQuery.getGeoId();

    CONCAT label = F.CONCAT(F.CONCAT(F.CONCAT(F.CONCAT(geoLabel, " ("), F.CONCAT(universalLabel, ")")), " : "), geoId);
    label.setColumnAlias(GeoEntity.DISPLAYLABEL);
    label.setUserDefinedAlias(GeoEntity.DISPLAYLABEL);
    label.setUserDefinedDisplayLabel(GeoEntity.DISPLAYLABEL);

    query.SELECT(id, label);
    query.WHERE(label.LIKEi("%" + text + "%"));
    query.AND(aptQuery.getParentTerm().EQ(country));
    query.AND(entityQuery.EQ(aptQuery.getChildTerm()));

    query.ORDER_BY_ASC(geoLabel);

    query.restrictRows(limit, 1);

    return query;
  }

  @Override
  public Boolean hasReport()
  {
    ReportItemQuery query = new ReportItemQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    return ( query.getCount() > 0 );
  }

  @Override
  public DashboardCondition[] getConditions()
  {
    DashboardState state = this.getDashboardState();
    String json = state.getConditions();

    DashboardCondition[] conditions = DashboardCondition.deserialize(json);

    return conditions;
  }

  @Override
  @Transaction
  public void applyConditions(DashboardCondition[] conditions)
  {
    /*
     * First delete any conditions which exist
     */
    GeodashboardUser user = GeodashboardUser.getCurrentUser();

    byte[] image = this.generateThumbnail();

    JSONArray array = new JSONArray();

    for (DashboardCondition condition : conditions)
    {
      array.put(condition.getJSON());
    }

    DashboardState state = DashboardState.getDashboardState(this, user);

    if (state == null)
    {
      state = new DashboardState();
      state.setDashboard(this);
      state.setGeodashboardUser(user);
    }
    else
    {
      state.lock();
    }

    state.setConditions(array.toString());
    state.setMapThumbnail(image);
    state.apply();
  }

  @Override
  @Transaction
  public void applyGlobalConditions(DashboardCondition[] conditions)
  {
    byte[] image = this.generateThumbnail();

    JSONArray array = new JSONArray();

    for (DashboardCondition condition : conditions)
    {
      array.put(condition.getJSON());
    }

    DashboardState state = DashboardState.getDashboardState(this, null);
    state.lock();
    state.setConditions(array.toString());
    state.setMapThumbnail(image);
    state.apply();
  }

  @Override
  public String getConditionsJSON()
  {
    DashboardState state = this.getDashboardState();
    String conditions = state.getConditions();

    if (conditions != null && conditions.length() > 0)
    {
      return conditions;
    }

    return "''";
  }

  @Override
  public Boolean hasAccess()
  {
    GeodashboardUser currentUser = GeodashboardUser.getCurrentUser();

    Boolean access = currentUser.isAssigned(this.getDashboardRole());

    if (!access)
    {
      return GeodashboardUser.hasAccess(AccessConstants.ADMIN);
    }

    return true;
  }

  /*
   * Gets all active geodashboard users in the system who are not Administrators
   */
  @Override
  public GeodashboardUser[] getAllDashboardUsers()
  {
    ArrayList<GeodashboardUser> nonAdminGDUsers = new ArrayList<GeodashboardUser>();
    GeodashboardUser[] gdUsers = GeodashboardUser.getAllUsers();
    for (int i = 0; i < gdUsers.length; i++)
    {
      boolean isAdmin = false;
      GeodashboardUser user = gdUsers[i];

      List<? extends Roles> userRoles = user.getAllAssignedRole().getAll();
      for (Roles role : userRoles)
      {
        if (role.getRoleName().equals(RoleView.ADMIN_NAMESPACE + ".Administrator"))
        {
          isAdmin = true;
        }
      }

      Boolean inactive = user.getInactive();

      if (!inactive && !isAdmin)
      {
        nonAdminGDUsers.add(user);
      }
    }

    GeodashboardUser[] nonAdminGDUsersArr = nonAdminGDUsers.toArray(new GeodashboardUser[nonAdminGDUsers.size()]);

    return nonAdminGDUsersArr;
  }

  /*
   * Gets all active geodashboard users in the system who are not Administrators and whether they already have access to
   * a given dashboard.
   */
  @Override
  public String getAllDashboardUsersJSON()
  {
    JSONArray usersArr = new JSONArray();

    GeodashboardUser[] gdUsers = this.getAllDashboardUsers();
    for (int i = 0; i < gdUsers.length; i++)
    {
      JSONObject userObj = new JSONObject();
      GeodashboardUser user = gdUsers[i];

      boolean hasAccess = this.userHasAccess(user);
      Boolean inactive = user.getInactive();

      if (!inactive)
      {
        try
        {
          userObj.put("firstName", user.getFirstName());
          userObj.put("lastName", user.getLastName());
          userObj.put("id", user.getId());
          userObj.put("hasAccess", hasAccess);
          usersArr.put(userObj);
        }
        catch (JSONException e)
        {
          String msg = "Could not properly build user json.";
          throw new ProgrammingErrorException(msg, e);
        }
      }
    }

    return usersArr.toString();
  }

  public static void assignUsers(String dashboardId, String[] userIds)
  {
    Dashboard dashboard = Dashboard.get(dashboardId);
    Roles dbRole = dashboard.getDashboardRole();
    String dbRoleId = dbRole.getId();
    Roles[] allGeodashRoles = RoleView.getGeodashboardRoles();

    for (String userJSON : userIds)
    {
      List<String> roleIds = new ArrayList<String>();
      Set<String> set;
      UserDAOIF user = null;
      try
      {
        String userId = null;
        JSONObject userObj = new JSONObject(userJSON);

        @SuppressWarnings("unchecked")
        Iterator<String> userObjKeys = userObj.keys();
        while (userObjKeys.hasNext())
        {
          userId = userObjKeys.next().toString();
        }

        GeodashboardUser gdUser = GeodashboardUser.get(userId);
        user = UserDAO.get(userId);

        boolean assignToDashboard = (Boolean) userObj.get(userId);
        if (assignToDashboard)
        {
          roleIds.add(dbRoleId);
        }

        List<? extends Roles> userRoles = gdUser.getAllAssignedRole().getAll();
        for (Roles existingRole : userRoles)
        {
          // filter out roles for this dashboard if it already exists on the user
          // because the dashboard role assignment happens above
          if (!dbRoleId.equals(existingRole.getId()))
          {
            roleIds.add(existingRole.getId());
          }
        }
      }
      catch (JSONException e)
      {
        String msg = "Could not properly parse user json.";
        throw new ProgrammingErrorException(msg, e);
      }

      set = new HashSet<String>(roleIds);

      /*
       * Assign roles
       */
      for (Roles role : allGeodashRoles)
      {
        RoleDAO roleDAO = RoleDAO.get(role.getId()).getBusinessDAO();

        if (set.contains(role.getId()))
        {
          roleDAO.assignMember(user);
        }
        else
        {
          roleDAO.deassignMember(user);
        }
      }
    }
  }

  private Boolean userHasAccess(GeodashboardUser user)
  {
    Boolean access = user.isAssigned(this.getDashboardRole());

    if (access)
    {
      return true;
    }

    return false;
  }

  public Map<String, Integer> getUniversalIndices()
  {
    Universal universal = this.getCountry().getUniversal();

    Collection<Term> children = GeoEntityUtil.getOrderedDescendants(universal, AllowedIn.CLASS);

    Map<String, Integer> indices = new HashMap<String, Integer>();

    int count = 0;

    indices.put(universal.getId(), count++);

    for (Term child : children)
    {
      indices.put(child.getId(), count++);
    }

    return indices;
  }

  public GeoNode[] getGeoNodes(MdAttribute thematicAttribute)
  {
    MdAttributeDAOIF thematicAttributeDAO = MdAttributeDAO.get(thematicAttribute.getId());

    return this.getGeoNodes(thematicAttributeDAO);
  }

  public GeoNode[] getGeoNodes(MdAttributeDAOIF thematicAttribute)
  {
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.AND(mcQuery.getWrappedMdClass().EQ(thematicAttribute.definedByClass()));

    MappableClassGeoNodeQuery mcgnQuery = new MappableClassGeoNodeQuery(factory);
    mcgnQuery.WHERE(mcgnQuery.getParent().EQ(mcQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mcgnQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    List<GeoNode> nodes = new LinkedList<GeoNode>();

    try
    {
      while (iterator.hasNext())
      {
        GeoNode geoNode = iterator.next();

        nodes.add(geoNode);
      }

      return nodes.toArray(new GeoNode[nodes.size()]);

    }
    finally
    {
      iterator.close();
    }
  }

  public GeoNode getGeoNode(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.AND(mcQuery.getWrappedMdClass().EQ(mdAttribute.definedByClass()));

    MappableClassGeoNodeQuery mcgnQuery = new MappableClassGeoNodeQuery(factory);
    mcgnQuery.WHERE(mcgnQuery.getParent().EQ(mcQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mcgnQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        GeoNode geoNode = iterator.next();
        // Geo entity node
        String geoEntityAttributeId = geoNode.getGeoEntityAttributeId();

        if (geoEntityAttributeId.equals(mdAttribute.getId()) || geoEntityAttributeId.equals(mdAttributeConcrete.getId()))
        {
          return geoNode;
        }
      }

      throw new ProgrammingErrorException("Unable to find a Geo Node for the Dashboard [" + this.getId() + "] and Attribute [" + mdAttribute.getId() + "]");
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public InputStream getThumbnailStream()
  {
    DashboardState state = this.getDashboardState();
    byte[] buffer = state.getMapThumbnail();

    return new ByteArrayInputStream(buffer);
  }

  private DashboardState getDashboardState()
  {
    GeodashboardUser user = GeodashboardUser.getCurrentUser();

    DashboardState state = null;

    if (user != null)
    {
      state = DashboardState.getDashboardState(this, user);
    }

    if (state == null)
    {
      state = DashboardState.getDashboardState(this, null);
    }

    return state;
  }

  @Override
  @Transaction
  public void generateThumbnailImage()
  {
//    byte[] image = this.generateThumbnail();
//
//    DashboardState state = DashboardState.getDashboardState(this, null);
//    state.lock();
//    state.setMapThumbnail(image);
//    state.apply();
  }

  @Transaction
  public byte[] generateThumbnail()
  {
    String outFileFormat = "png";
    BufferedImage base = null;
    Graphics mapBaseGraphic = null;
    BufferedImage resizedImage = null;
    int defaultWidth = 800;
    int defaultHeight = 750;
    Double bottom;
    Double top;
    Double right;
    Double left;
    JSONObject restructuredBounds = new JSONObject();

    DashboardMap dashMap = this.getMap();

    // Ordering the layers from the default map
    DashboardLayer[] orderedLayers = dashMap.getOrderedLayers();
    JSONArray mapBoundsArr = dashMap.getExpandedMapLayersBBox(orderedLayers, .5);

    // Get bounds of the map
    try
    {
      left = mapBoundsArr.getDouble(0);
      bottom = mapBoundsArr.getDouble(1);
      right = mapBoundsArr.getDouble(2);
      top = mapBoundsArr.getDouble(3);

      restructuredBounds.put("left", left);
      restructuredBounds.put("bottom", bottom);
      restructuredBounds.put("right", right);
      restructuredBounds.put("top", top);
    }
    catch (JSONException e)
    {
      String error = "Could not parse map bounds.";
      throw new ProgrammingErrorException(error, e);
    }

    int width = (int) Math.min(defaultWidth, Math.round( ( ( ( right - left ) / ( top - bottom ) ) * defaultHeight )));
    int height = (int) Math.min(defaultHeight, Math.round( ( ( ( top - bottom ) / ( right - left ) ) * defaultWidth )));

    base = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    // Create the base canvas that all other map elements will be draped on top of
    mapBaseGraphic = base.getGraphics();
    mapBaseGraphic.setColor(Color.white);
    mapBaseGraphic.fillRect(0, 0, width, height);
    mapBaseGraphic.drawImage(base, 0, 0, null);

    // Get base map
    String activeBaseMap = dashMap.getActiveBaseMap();
    if (activeBaseMap.length() > 0)
    {
      String baseType = null;
      try
      {
        JSONObject activeBaseObj = new JSONObject(activeBaseMap);
        baseType = activeBaseObj.getString("LAYER_SOURCE_TYPE");
      }
      catch (JSONException e)
      {
        String error = "Could not parse active base map JSON.";
        throw new ProgrammingErrorException(error, e);
      }

      if (baseType.length() > 0)
      {
        BufferedImage baseMapImage = dashMap.getBaseMapCanvas(width, height, Double.toString(left), Double.toString(bottom), Double.toString(right), Double.toString(top));

        if (baseMapImage != null)
        {
          mapBaseGraphic.drawImage(baseMapImage, 0, 0, null);
        }
      }
    }

    // Add layers to the base canvas
    BufferedImage layerCanvas = dashMap.getLayersExportCanvas(width, height, orderedLayers, restructuredBounds.toString());

    // Offset the layerCanvas so that it is center
    int widthOffset = (int) ( ( width - layerCanvas.getWidth() ) / 2 );
    int heightOffset = (int) ( ( height - layerCanvas.getHeight() ) / 2 );

    mapBaseGraphic.drawImage(layerCanvas, widthOffset, heightOffset, null);

    try
    {
      resizedImage = Thumbnails.of(base).size(210, 210).asBufferedImage();
    }
    catch (IOException e)
    {
      String error = "Could not resize map image to thumbnail size.";
      throw new ProgrammingErrorException(error, e);
    }

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    try
    {
      ImageIO.write(resizedImage, outFileFormat, outStream);
    }
    catch (IOException e)
    {
      String error = "Could not write map image to the output stream.";
      throw new ProgrammingErrorException(error, e);
    }
    finally
    {
      if (outStream != null)
      {
        try
        {
          outStream.flush();
          outStream.close();
        }
        catch (IOException e)
        {
          String error = "Could not close stream.";
          throw new ProgrammingErrorException(error, e);
        }
      }
    }

    if (mapBaseGraphic != null)
    {
      mapBaseGraphic.dispose();
    }

    return outStream.toByteArray();
  }

  @Override
  @Transaction
  public void setBaseLayerState(String baseLayerState)
  {
    DashboardMap dm = this.getMap();
    dm.lock();
    dm.setActiveBaseMap(baseLayerState);
    dm.unlock();
  }
}
