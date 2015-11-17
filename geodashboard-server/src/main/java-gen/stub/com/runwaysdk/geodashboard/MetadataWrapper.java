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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.gis.geo.GeoNode;
import com.runwaysdk.system.gis.geo.GeoNodeQuery;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdClassQuery;

public class MetadataWrapper extends MetadataWrapperBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1121470685;

  public MetadataWrapper()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    Dashboard dashboard = this.getDashboard();
    MdClass wrappedMdClass = this.getWrappedMdClass();

    if (dashboard != null && wrappedMdClass != null)
    {
      return dashboard.getName() + "-" + wrappedMdClass.getKey();
    }

    return super.buildKey();
  }

  @Override
  public void delete()
  {
    Dashboard dashboard = this.getDashboard();

    for (AttributeWrapper aw : this.getAllAttributeWrapper())
    {
      aw.delete(dashboard);
    }

    super.delete();
  }

  public MdAttributeView[] getSortedAttributes()
  {
    String mdId = this.getWrappedMdClassId();

    List<MdAttributeView> mdAttr = new LinkedList<MdAttributeView>();
    Locale locale = Session.getCurrentLocale();

    QueryFactory f = new QueryFactory();

    DashboardAttributesQuery daQ = new DashboardAttributesQuery(f);

    daQ.WHERE(daQ.parentId().EQ(this.getId()));
    daQ.ORDER_BY_ASC(daQ.getListOrder());

    OIterator<? extends DashboardAttributes> iter = daQ.getIterator();
    try
    {
      while (iter.hasNext())
      {
        DashboardAttributes attribute = iter.next();
        AttributeWrapper aWrapper = attribute.getChild();

        MdAttributeDAOIF attr = MdAttributeDAO.get(aWrapper.getWrappedMdAttributeId());
        MdAttributeConcreteDAOIF mdAttributeConcrete = attr.getMdAttributeConcrete();

        String attrId = attr.getId();
        String attrType = mdAttributeConcrete.getType();

        MdAttributeView view = new MdAttributeView();

        String label = attr.getDisplayLabel(locale);

        view.setMdClassId(mdId);
        view.setMdAttributeId(attrId);
        view.setDisplayLabel(label);
        view.setAttributeName(attr.definesAttribute());
        view.setAttributeType(attrType);

        mdAttr.add(view);
      }
    }
    finally
    {
      iter.close();
    }

    return mdAttr.toArray(new MdAttributeView[mdAttr.size()]);
  }

  public static List<MdClass> getMdClassesWithGeoNodes()
  {
    // Returns list of all types defined with GeoNodes
    QueryFactory factory = new QueryFactory();

    MappableClassGeoNodeQuery mgQuery = new MappableClassGeoNodeQuery(factory);

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.WHERE(mcQuery.EQ(mgQuery.getParent()));

    MdClassQuery query = new MdClassQuery(factory);
    query.WHERE(query.EQ(mcQuery.getWrappedMdClass()));

    OIterator<? extends MdClass> iterator = query.getIterator();

    try
    {
      List<? extends MdClass> results = iterator.getAll();

      return new LinkedList<MdClass>(results);
    }
    finally
    {
      iterator.close();
    }

  }

  public static List<GeoNode> getGeoNodes(String type)
  {
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mwQuery = new MappableClassQuery(factory);
    mwQuery.AND(mwQuery.getWrappedMdClass().EQ(MdClassDAO.getMdClassDAO(type)));

    MappableClassGeoNodeQuery mgQuery = new MappableClassGeoNodeQuery(factory);
    mgQuery.WHERE(mgQuery.getParent().EQ(mwQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mgQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    try
    {
      List<? extends GeoNode> nodes = iterator.getAll();

      return new LinkedList<GeoNode>(nodes);
    }
    finally
    {
      iterator.close();
    }
  }

  public MetadataWrapper clone(Dashboard dashboard)
  {
    MetadataWrapper clone = new MetadataWrapper();
    clone.setDashboard(dashboard);
    clone.setWrappedMdClass(this.getWrappedMdClass());
    clone.apply();

    OIterator<? extends DashboardAttributes> attributes = this.getAllAttributeWrapperRel();

    for (DashboardAttributes attribute : attributes)
    {
      AttributeWrapper child = attribute.getChild();
      child.clone(clone, attribute.getListOrder());
    }

    return clone;
  }

}
