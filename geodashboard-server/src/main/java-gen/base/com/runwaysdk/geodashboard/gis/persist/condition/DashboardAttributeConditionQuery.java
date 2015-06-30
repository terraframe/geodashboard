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

@com.runwaysdk.business.ClassSignature(hash = -69461591)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardAttributeCondition.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DashboardAttributeConditionQuery extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DashboardAttributeConditionQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DashboardAttributeConditionQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.CLASS;
  }
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute()
  {
    return getDefiningMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE);

    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE);

    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends DashboardAttributeCondition> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<DashboardAttributeCondition>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardAttributeConditionQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF
  {

    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute();
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias);
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition dashboardAttributeCondition);

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition dashboardAttributeCondition);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardAttributeConditionQueryReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReference
 implements DashboardAttributeConditionQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardAttributeConditionQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition dashboardAttributeCondition)
    {
      if(dashboardAttributeCondition == null) return this.EQ((java.lang.String)null);
      return this.EQ(dashboardAttributeCondition.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition dashboardAttributeCondition)
    {
      if(dashboardAttributeCondition == null) return this.NE((java.lang.String)null);
      return this.NE(dashboardAttributeCondition.getId());
    }

  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute()
  {
    return getDefiningMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardAttributeConditionQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryMultiReferenceIF
  {

    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute();
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias);
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition);
    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition);
    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition);
    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition);
    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardAttributeConditionQueryMultiReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryMultiReference
 implements DashboardAttributeConditionQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardAttributeConditionQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition)  {

      String[] itemIdArray = new String[dashboardAttributeCondition.length]; 

      for (int i=0; i<dashboardAttributeCondition.length; i++)
      {
        itemIdArray[i] = dashboardAttributeCondition[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition)  {

      String[] itemIdArray = new String[dashboardAttributeCondition.length]; 

      for (int i=0; i<dashboardAttributeCondition.length; i++)
      {
        itemIdArray[i] = dashboardAttributeCondition[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition)  {

      String[] itemIdArray = new String[dashboardAttributeCondition.length]; 

      for (int i=0; i<dashboardAttributeCondition.length; i++)
      {
        itemIdArray[i] = dashboardAttributeCondition[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition)  {

      String[] itemIdArray = new String[dashboardAttributeCondition.length]; 

      for (int i=0; i<dashboardAttributeCondition.length; i++)
      {
        itemIdArray[i] = dashboardAttributeCondition[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition ... dashboardAttributeCondition)  {

      String[] itemIdArray = new String[dashboardAttributeCondition.length]; 

      for (int i=0; i<dashboardAttributeCondition.length; i++)
      {
        itemIdArray[i] = dashboardAttributeCondition[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute()
  {
    return getDefiningMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getDefiningMdAttribute(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition.DEFININGMDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
