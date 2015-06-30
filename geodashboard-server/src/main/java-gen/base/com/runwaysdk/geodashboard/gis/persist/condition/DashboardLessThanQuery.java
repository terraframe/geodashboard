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
package com.runwaysdk.geodashboard.gis.persist.condition;

@com.runwaysdk.business.ClassSignature(hash = -455936535)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardLessThan.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DashboardLessThanQuery extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DashboardLessThanQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DashboardLessThanQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends DashboardLessThan> getIterator()
  {
    this.checkNotUsedInValueQuery();
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<DashboardLessThan>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardLessThanQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan dashboardLessThan);

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan dashboardLessThan);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardLessThanQueryReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryReference
 implements DashboardLessThanQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardLessThanQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan dashboardLessThan)
    {
      if(dashboardLessThan == null) return this.EQ((java.lang.String)null);
      return this.EQ(dashboardLessThan.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan dashboardLessThan)
    {
      if(dashboardLessThan == null) return this.NE((java.lang.String)null);
      return this.NE(dashboardLessThan.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardLessThanQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan);
    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan);
    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan);
    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan);
    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardLessThanQueryMultiReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryMultiReference
 implements DashboardLessThanQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardLessThanQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan)  {

      String[] itemIdArray = new String[dashboardLessThan.length]; 

      for (int i=0; i<dashboardLessThan.length; i++)
      {
        itemIdArray[i] = dashboardLessThan[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan)  {

      String[] itemIdArray = new String[dashboardLessThan.length]; 

      for (int i=0; i<dashboardLessThan.length; i++)
      {
        itemIdArray[i] = dashboardLessThan[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan)  {

      String[] itemIdArray = new String[dashboardLessThan.length]; 

      for (int i=0; i<dashboardLessThan.length; i++)
      {
        itemIdArray[i] = dashboardLessThan[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan)  {

      String[] itemIdArray = new String[dashboardLessThan.length]; 

      for (int i=0; i<dashboardLessThan.length; i++)
      {
        itemIdArray[i] = dashboardLessThan[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan ... dashboardLessThan)  {

      String[] itemIdArray = new String[dashboardLessThan.length]; 

      for (int i=0; i<dashboardLessThan.length; i++)
      {
        itemIdArray[i] = dashboardLessThan[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
