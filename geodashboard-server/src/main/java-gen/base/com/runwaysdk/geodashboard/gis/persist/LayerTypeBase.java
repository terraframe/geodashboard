package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = -1782435717)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LayerType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LayerTypeBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.LayerType";
  private static final long serialVersionUID = -1782435717;
  
  public LayerTypeBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LayerTypeQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LayerTypeQuery query = new LayerTypeQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LayerType get(String id)
  {
    return (LayerType) com.runwaysdk.business.Business.get(id);
  }
  
  public static LayerType getByKey(String key)
  {
    return (LayerType) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static LayerType getEnumeration(String enumName)
  {
    return (LayerType) com.runwaysdk.business.Business.getEnumeration(com.runwaysdk.geodashboard.gis.persist.LayerType.CLASS ,enumName);
  }
  
  public static LayerType lock(java.lang.String id)
  {
    LayerType _instance = LayerType.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LayerType unlock(java.lang.String id)
  {
    LayerType _instance = LayerType.get(id);
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