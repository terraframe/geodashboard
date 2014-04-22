package com.runwaysdk.geodashboard.gis.persist.condition;

@com.runwaysdk.business.ClassSignature(hash = 1861160594)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardPrimitive.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DashboardPrimitiveQuery extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DashboardPrimitiveQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DashboardPrimitiveQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getComparisonValue()
  {
    return getComparisonValue(null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends DashboardPrimitive> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<DashboardPrimitive>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardPrimitiveQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF
  {

    public com.runwaysdk.query.SelectableChar getComparisonValue();
    public com.runwaysdk.query.SelectableChar getComparisonValue(String alias);
    public com.runwaysdk.query.SelectableChar getComparisonValue(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive dashboardPrimitive);

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive dashboardPrimitive);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardPrimitiveQueryReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReference
 implements DashboardPrimitiveQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardPrimitiveQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive dashboardPrimitive)
    {
      if(dashboardPrimitive == null) return this.EQ((java.lang.String)null);
      return this.EQ(dashboardPrimitive.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive dashboardPrimitive)
    {
      if(dashboardPrimitive == null) return this.NE((java.lang.String)null);
      return this.NE(dashboardPrimitive.getId());
    }

  public com.runwaysdk.query.SelectableChar getComparisonValue()
  {
    return getComparisonValue(null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, displayLabel);

  }
  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardPrimitiveQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryMultiReferenceIF
  {

    public com.runwaysdk.query.SelectableChar getComparisonValue();
    public com.runwaysdk.query.SelectableChar getComparisonValue(String alias);
    public com.runwaysdk.query.SelectableChar getComparisonValue(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive);
    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive);
    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive);
    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive);
    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardPrimitiveQueryMultiReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryMultiReference
 implements DashboardPrimitiveQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardPrimitiveQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive)  {

      String[] itemIdArray = new String[dashboardPrimitive.length]; 

      for (int i=0; i<dashboardPrimitive.length; i++)
      {
        itemIdArray[i] = dashboardPrimitive[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive)  {

      String[] itemIdArray = new String[dashboardPrimitive.length]; 

      for (int i=0; i<dashboardPrimitive.length; i++)
      {
        itemIdArray[i] = dashboardPrimitive[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive)  {

      String[] itemIdArray = new String[dashboardPrimitive.length]; 

      for (int i=0; i<dashboardPrimitive.length; i++)
      {
        itemIdArray[i] = dashboardPrimitive[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive)  {

      String[] itemIdArray = new String[dashboardPrimitive.length]; 

      for (int i=0; i<dashboardPrimitive.length; i++)
      {
        itemIdArray[i] = dashboardPrimitive[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive ... dashboardPrimitive)  {

      String[] itemIdArray = new String[dashboardPrimitive.length]; 

      for (int i=0; i<dashboardPrimitive.length; i++)
      {
        itemIdArray[i] = dashboardPrimitive[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.query.SelectableChar getComparisonValue()
  {
    return getComparisonValue(null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getComparisonValue(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive.COMPARISONVALUE, alias, displayLabel);

  }
  }
}