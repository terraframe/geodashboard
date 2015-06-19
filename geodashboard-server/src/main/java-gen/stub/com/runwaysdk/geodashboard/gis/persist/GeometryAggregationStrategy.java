package com.runwaysdk.geodashboard.gis.persist;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.geodashboard.QueryUtil;
import com.runwaysdk.geodashboard.gis.EmptyLayerInformation;
import com.runwaysdk.geodashboard.gis.geoserver.GeoserverFacade;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeCharacter;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.system.gis.geo.GeoNode;

public class GeometryAggregationStrategy extends GeometryAggregationStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 178551989;

  public static final Log    log              = LogFactory.getLog(GeometryAggregationStrategy.class);

  /**
   * Hard-coded magic value representing the use of the Geometry aggregation strategy
   */
  public static final String VALUE            = "GEOMETRY";

  public GeometryAggregationStrategy()
  {
    super();
  }

  @Override
  public ValueQuery getViewQuery(DashboardThematicLayer layer)
  {
    GeoNode node = layer.getGeoNode();

    QueryFactory factory = new QueryFactory();

    // Query containing the aggregated values and geo entity ids
    ValueQuery valueQuery = this.getThematicValueQuery(factory, layer);

    // Query to join with the geo entity geometries
    ValueQuery geometryQuery = new ValueQuery(factory);

    // Outer query joining the value queries and the geometry query
    ValueQuery outerQuery = new ValueQuery(factory);

    if (log.isDebugEnabled())
    {
      // print the SQL if the generated
      log.debug("SLD for Layer [%s], this:\n" + valueQuery.getSQL());
    }

    if (valueQuery.getCount() == 0)
    {
      EmptyLayerInformation info = new EmptyLayerInformation();
      info.setLayerName(layer.getName());
      info.apply();

      info.throwIt();
    }

    // Set the GeoID and the Geometry attribute for the second query
    MdClassDAOIF mdClass = layer.getMdAttributeDAO().definedByClass();

    GeneratedComponentQuery entityQuery = QueryUtil.getQuery(mdClass, factory);

    String identifierAttribute = node.getIdentifierAttribute().getAttributeName();

    Selectable geoId2 = entityQuery.get(identifierAttribute, GeoEntity.GEOID);
    geoId2.setColumnAlias(GeoEntity.GEOID);
    geoId2.setUserDefinedAlias(GeoEntity.GEOID);

    // geometry
    String columnName = this.getGeometryColumn(layer);

    Selectable geom = entityQuery.get(columnName, GeoserverFacade.GEOM_COLUMN);
    geom.setColumnAlias(GeoserverFacade.GEOM_COLUMN);
    geom.setUserDefinedAlias(GeoserverFacade.GEOM_COLUMN);

    geometryQuery.SELECT_DISTINCT(geoId2, geom);

    // Add all of the selectables from the values query to the outer query
    for (Selectable selectable : valueQuery.getSelectableRefs())
    {
      Attribute attribute = valueQuery.get(selectable.getResultAttributeName());
      attribute.setColumnAlias(selectable.getColumnAlias());

      outerQuery.SELECT(attribute);
    }

    // Add the geometry selectable from the geometry query to the outer query
    Attribute geomAttribute = geometryQuery.get(GeoserverFacade.GEOM_COLUMN);
    geomAttribute.setColumnAlias(GeoserverFacade.GEOM_COLUMN);

    outerQuery.SELECT(geomAttribute);

    // Join the geometry query to the values query through the geo id
    AttributeCharacter geometrySelectable = geometryQuery.aCharacter(GeoEntity.GEOID);

    outerQuery.WHERE(geometrySelectable.EQ(valueQuery.aCharacter(GeoEntity.GEOID)));

    return outerQuery;
  }

  private ValueQuery getThematicValueQuery(QueryFactory factory, DashboardThematicLayer layer)
  {
    ThematicQueryBuilder builder = new GeometryThematicQueryBuilder(factory, layer);
    ValueQuery valueQuery = builder.getThematicValueQuery();

    return valueQuery;
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      JSONObject object = new JSONObject();
      object.put("type", this.getClass().getSimpleName());
      object.put("value", VALUE);

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public String getCategoryLabel(GeoNode geoNode, String categoryId)
  {
    MdAttributeDAOIF identifierAttribute = MdAttributeDAO.get(geoNode.getIdentifierAttribute().getId());
    MdAttributeDAOIF displayLabelAttribute = MdAttributeDAO.get(geoNode.getDisplayLabelAttribute().getId());
    String attributeName = displayLabelAttribute.definesAttribute();

    MdClassDAOIF mdClass = identifierAttribute.definedByClass();

    QueryFactory factory = new QueryFactory();

    ValueQuery vQuery = new ValueQuery(factory);

    GeneratedComponentQuery query = QueryUtil.getQuery(mdClass, vQuery);
    Selectable labelAttribute = query.get(attributeName);

    if (labelAttribute instanceof AttributeLocal)
    {
      labelAttribute = ( (AttributeLocal) labelAttribute ).localize();
      labelAttribute.setColumnAlias(attributeName);
      labelAttribute.setUserDefinedAlias(attributeName);
    }

    vQuery.SELECT(labelAttribute);
    vQuery.WHERE(query.get(identifierAttribute.definesAttribute()).EQ(categoryId));

    OIterator<ValueObject> iterator = vQuery.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        ValueObject object = iterator.next();
        String label = object.getValue(attributeName);

        return label;
      }

      throw new ProgrammingErrorException("Unable to retrieve a display label for a geometry strategy object.  This should never happen");
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public AggregationStrategy clone()
  {
    GeometryAggregationStrategy clone = new GeometryAggregationStrategy();
    clone.apply();

    return clone;
  }
}