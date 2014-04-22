package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = 904131610)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardThematicStyle.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DashboardThematicStyleQuery extends com.runwaysdk.geodashboard.gis.persist.DashboardStyleQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DashboardThematicStyleQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DashboardThematicStyleQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.CLASS;
  }
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute()
  {
    return getMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE);

    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE);

    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition()
  {
    return getStyleCondition(null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION);

    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION);

    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION)) 
    {
       return new com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.runwaysdk.query.OIterator<? extends DashboardThematicStyle> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<DashboardThematicStyle>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardThematicStyleQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.DashboardStyleQuery.DashboardStyleQueryReferenceIF
  {

    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute();
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias);
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias, String displayLabel);
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition();
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias);
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle dashboardThematicStyle);

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle dashboardThematicStyle);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardThematicStyleQueryReference extends com.runwaysdk.geodashboard.gis.persist.DashboardStyleQuery.DashboardStyleQueryReference
 implements DashboardThematicStyleQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardThematicStyleQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle dashboardThematicStyle)
    {
      if(dashboardThematicStyle == null) return this.EQ((java.lang.String)null);
      return this.EQ(dashboardThematicStyle.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle dashboardThematicStyle)
    {
      if(dashboardThematicStyle == null) return this.NE((java.lang.String)null);
      return this.NE(dashboardThematicStyle.getId());
    }

  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute()
  {
    return getMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE,  alias, displayLabel);

  }
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition()
  {
    return getStyleCondition(null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias)
  {
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION, alias, null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias, String displayLabel)
  {
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION)) 
    {
       return new com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public interface DashboardThematicStyleQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.DashboardStyleQuery.DashboardStyleQueryMultiReferenceIF
  {

    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute();
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias);
    public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias, String displayLabel);
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition();
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias);
    public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle);
    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle);
    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle);
    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle);
    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardThematicStyleQueryMultiReference extends com.runwaysdk.geodashboard.gis.persist.DashboardStyleQuery.DashboardStyleQueryMultiReference
 implements DashboardThematicStyleQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardThematicStyleQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle)  {

      String[] itemIdArray = new String[dashboardThematicStyle.length]; 

      for (int i=0; i<dashboardThematicStyle.length; i++)
      {
        itemIdArray[i] = dashboardThematicStyle[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle)  {

      String[] itemIdArray = new String[dashboardThematicStyle.length]; 

      for (int i=0; i<dashboardThematicStyle.length; i++)
      {
        itemIdArray[i] = dashboardThematicStyle[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle)  {

      String[] itemIdArray = new String[dashboardThematicStyle.length]; 

      for (int i=0; i<dashboardThematicStyle.length; i++)
      {
        itemIdArray[i] = dashboardThematicStyle[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle)  {

      String[] itemIdArray = new String[dashboardThematicStyle.length]; 

      for (int i=0; i<dashboardThematicStyle.length; i++)
      {
        itemIdArray[i] = dashboardThematicStyle[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle ... dashboardThematicStyle)  {

      String[] itemIdArray = new String[dashboardThematicStyle.length]; 

      for (int i=0; i<dashboardThematicStyle.length; i++)
      {
        itemIdArray[i] = dashboardThematicStyle[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute()
  {
    return getMdAttribute(null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE, alias, null);

  }
 
  public com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF getMdAttribute(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE,  alias, displayLabel);

  }
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition()
  {
    return getStyleCondition(null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias)
  {
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION, alias, null);

  }
 
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF getStyleCondition(String alias, String displayLabel)
  {
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReferenceIF)this.get(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.MDATTRIBUTE)) 
    {
       return new com.runwaysdk.system.metadata.MdAttributeQuery.MdAttributeQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals(com.runwaysdk.geodashboard.gis.persist.DashboardThematicStyle.STYLECONDITION)) 
    {
       return new com.runwaysdk.geodashboard.gis.persist.condition.DashboardConditionQuery.DashboardConditionQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}