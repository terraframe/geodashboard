package com.runwaysdk.geodashboard.gis.persist.condition;

@com.runwaysdk.business.ClassSignature(hash = 72798277)
public abstract class DashboardGreaterThanDTOBase extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan";
  private static final long serialVersionUID = 72798277;
  
  protected DashboardGreaterThanDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardGreaterThanDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
  public static com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanQueryDTO) clientRequest.getAllInstances(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO.CLASS, "lock", _declaredTypes);
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO.CLASS, "unlock", _declaredTypes);
    return (com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}