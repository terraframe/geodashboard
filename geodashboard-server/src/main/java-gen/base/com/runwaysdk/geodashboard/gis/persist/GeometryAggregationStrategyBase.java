package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = -250280)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeometryAggregationStrategy.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeometryAggregationStrategyBase extends com.runwaysdk.geodashboard.gis.persist.AggregationStrategy implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.GeometryAggregationStrategy";
  private static final long serialVersionUID = -250280;
  
  public GeometryAggregationStrategyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeometryAggregationStrategyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GeometryAggregationStrategyQuery query = new GeometryAggregationStrategyQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static GeometryAggregationStrategy get(String id)
  {
    return (GeometryAggregationStrategy) com.runwaysdk.business.Business.get(id);
  }
  
  public static GeometryAggregationStrategy getByKey(String key)
  {
    return (GeometryAggregationStrategy) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static GeometryAggregationStrategy lock(java.lang.String id)
  {
    GeometryAggregationStrategy _instance = GeometryAggregationStrategy.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GeometryAggregationStrategy unlock(java.lang.String id)
  {
    GeometryAggregationStrategy _instance = GeometryAggregationStrategy.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}