package com.runwaysdk.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = -148315227)
public abstract class GeodashboardUserDTOBase extends com.runwaysdk.system.UsersDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.GeodashboardUser";
  private static final long serialVersionUID = -148315227;
  
  protected GeodashboardUserDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeodashboardUserDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EMAIL = "email";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String PHONENUMBER = "phoneNumber";
  public String getEmail()
  {
    return getValue(EMAIL);
  }
  
  public void setEmail(String value)
  {
    if(value == null)
    {
      setValue(EMAIL, "");
    }
    else
    {
      setValue(EMAIL, value);
    }
  }
  
  public boolean isEmailWritable()
  {
    return isWritable(EMAIL);
  }
  
  public boolean isEmailReadable()
  {
    return isReadable(EMAIL);
  }
  
  public boolean isEmailModified()
  {
    return isModified(EMAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEmailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EMAIL).getAttributeMdDTO();
  }
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void setFirstName(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAME, "");
    }
    else
    {
      setValue(FIRSTNAME, value);
    }
  }
  
  public boolean isFirstNameWritable()
  {
    return isWritable(FIRSTNAME);
  }
  
  public boolean isFirstNameReadable()
  {
    return isReadable(FIRSTNAME);
  }
  
  public boolean isFirstNameModified()
  {
    return isModified(FIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
  }
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void setLastName(String value)
  {
    if(value == null)
    {
      setValue(LASTNAME, "");
    }
    else
    {
      setValue(LASTNAME, value);
    }
  }
  
  public boolean isLastNameWritable()
  {
    return isWritable(LASTNAME);
  }
  
  public boolean isLastNameReadable()
  {
    return isReadable(LASTNAME);
  }
  
  public boolean isLastNameModified()
  {
    return isModified(LASTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
  }
  
  public String getPhoneNumber()
  {
    return getValue(PHONENUMBER);
  }
  
  public void setPhoneNumber(String value)
  {
    if(value == null)
    {
      setValue(PHONENUMBER, "");
    }
    else
    {
      setValue(PHONENUMBER, value);
    }
  }
  
  public boolean isPhoneNumberWritable()
  {
    return isWritable(PHONENUMBER);
  }
  
  public boolean isPhoneNumberReadable()
  {
    return isReadable(PHONENUMBER);
  }
  
  public boolean isPhoneNumberModified()
  {
    return isModified(PHONENUMBER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPhoneNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PHONENUMBER).getAttributeMdDTO();
  }
  
  public final void applyWithRoles(java.lang.String[] roleIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{roleIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, "applyWithRoles", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyWithRoles(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String[] roleIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, roleIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, "applyWithRoles", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.geodashboard.GeodashboardUserDTO getCurrentUser(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, "getCurrentUser", _declaredTypes);
    return (com.runwaysdk.geodashboard.GeodashboardUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static com.runwaysdk.geodashboard.GeodashboardUserDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (com.runwaysdk.geodashboard.GeodashboardUserDTO) dto;
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
  
  public static com.runwaysdk.geodashboard.GeodashboardUserQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (com.runwaysdk.geodashboard.GeodashboardUserQueryDTO) clientRequest.getAllInstances(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static com.runwaysdk.geodashboard.GeodashboardUserDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, "lock", _declaredTypes);
    return (com.runwaysdk.geodashboard.GeodashboardUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static com.runwaysdk.geodashboard.GeodashboardUserDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(com.runwaysdk.geodashboard.GeodashboardUserDTO.CLASS, "unlock", _declaredTypes);
    return (com.runwaysdk.geodashboard.GeodashboardUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}