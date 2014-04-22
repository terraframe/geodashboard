package com.runwaysdk.geodashboard.gis.persist.condition;

@com.runwaysdk.business.ClassSignature(hash = -409805651)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardEqual.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardEqualBase extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitive implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.condition.DashboardEqual";
  public static java.lang.String ATTRIBUTEVALUE = "attributeValue";
  private static final long serialVersionUID = -409805651;
  
  public DashboardEqualBase()
  {
    super();
  }
  
  public String getAttributeValue()
  {
    return getValue(ATTRIBUTEVALUE);
  }
  
  public void validateAttributeValue()
  {
    this.validateAttribute(ATTRIBUTEVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAttributeValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.condition.DashboardEqual.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTEVALUE);
  }
  
  public void setAttributeValue(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEVALUE, "");
    }
    else
    {
      setValue(ATTRIBUTEVALUE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardEqualQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardEqualQuery query = new DashboardEqualQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DashboardEqual get(String id)
  {
    return (DashboardEqual) com.runwaysdk.business.Business.get(id);
  }
  
  public static DashboardEqual getByKey(String key)
  {
    return (DashboardEqual) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static DashboardEqual lock(java.lang.String id)
  {
    DashboardEqual _instance = DashboardEqual.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DashboardEqual unlock(java.lang.String id)
  {
    DashboardEqual _instance = DashboardEqual.get(id);
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
