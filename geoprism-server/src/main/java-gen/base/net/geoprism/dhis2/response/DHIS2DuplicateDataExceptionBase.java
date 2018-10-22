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
package net.geoprism.dhis2.response;

@com.runwaysdk.business.ClassSignature(hash = -633349557)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DHIS2DuplicateDataException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DHIS2DuplicateDataExceptionBase extends com.runwaysdk.business.SmartException 
{
  public final static String CLASS = "net.geoprism.dhis2.response.DHIS2DuplicateDataException";
  public static java.lang.String DATATYPE = "dataType";
  public static java.lang.String OID = "oid";
  public static java.lang.String PROPERTYNAME = "propertyName";
  public static java.lang.String PROPERTYVALUE = "propertyValue";
  private static final long serialVersionUID = -633349557;
  
  public DHIS2DuplicateDataExceptionBase()
  {
    super();
  }
  
  public DHIS2DuplicateDataExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DHIS2DuplicateDataExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DHIS2DuplicateDataExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getDataType()
  {
    return getValue(DATATYPE);
  }
  
  public void validateDataType()
  {
    this.validateAttribute(DATATYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDataTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dhis2.response.DHIS2DuplicateDataException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DATATYPE);
  }
  
  public void setDataType(String value)
  {
    if(value == null)
    {
      setValue(DATATYPE, "");
    }
    else
    {
      setValue(DATATYPE, value);
    }
  }
  
  public String getOid()
  {
    return getValue(OID);
  }
  
  public void validateId()
  {
    this.validateAttribute(OID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getOidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dhis2.response.DHIS2DuplicateDataException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OID);
  }
  
  public String getPropertyName()
  {
    return getValue(PROPERTYNAME);
  }
  
  public void validatePropertyName()
  {
    this.validateAttribute(PROPERTYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getPropertyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dhis2.response.DHIS2DuplicateDataException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PROPERTYNAME);
  }
  
  public void setPropertyName(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYNAME, "");
    }
    else
    {
      setValue(PROPERTYNAME, value);
    }
  }
  
  public String getPropertyValue()
  {
    return getValue(PROPERTYVALUE);
  }
  
  public void validatePropertyValue()
  {
    this.validateAttribute(PROPERTYVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getPropertyValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dhis2.response.DHIS2DuplicateDataException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PROPERTYVALUE);
  }
  
  public void setPropertyValue(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYVALUE, "");
    }
    else
    {
      setValue(PROPERTYVALUE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{dataType}", this.getDataType());
    message = replace(message, "{oid}", this.getOid());
    message = replace(message, "{propertyName}", this.getPropertyName());
    message = replace(message, "{propertyValue}", this.getPropertyValue());
    return message;
  }
  
}