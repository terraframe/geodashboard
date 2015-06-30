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
package com.runwaysdk.geodashboard.databrowser;

@com.runwaysdk.business.ClassSignature(hash = -1611134155)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MetadataType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MetadataTypeBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.databrowser.MetadataType";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTYPEID = "parentTypeId";
  public static java.lang.String TYPEID = "typeId";
  public static java.lang.String TYPENAME = "typeName";
  public static java.lang.String TYPEPACKAGE = "typePackage";
  private static final long serialVersionUID = -1611134155;
  
  public MetadataTypeBase()
  {
    super();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getParentTypeId()
  {
    return getValue(PARENTTYPEID);
  }
  
  public void validateParentTypeId()
  {
    this.validateAttribute(PARENTTYPEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getParentTypeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(PARENTTYPEID);
  }
  
  public void setParentTypeId(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPEID, "");
    }
    else
    {
      setValue(PARENTTYPEID, value);
    }
  }
  
  public String getTypeId()
  {
    return getValue(TYPEID);
  }
  
  public void validateTypeId()
  {
    this.validateAttribute(TYPEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(TYPEID);
  }
  
  public void setTypeId(String value)
  {
    if(value == null)
    {
      setValue(TYPEID, "");
    }
    else
    {
      setValue(TYPEID, value);
    }
  }
  
  public String getTypeName()
  {
    return getValue(TYPENAME);
  }
  
  public void validateTypeName()
  {
    this.validateAttribute(TYPENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(TYPENAME);
  }
  
  public void setTypeName(String value)
  {
    if(value == null)
    {
      setValue(TYPENAME, "");
    }
    else
    {
      setValue(TYPENAME, value);
    }
  }
  
  public String getTypePackage()
  {
    return getValue(TYPEPACKAGE);
  }
  
  public void validateTypePackage()
  {
    this.validateAttribute(TYPEPACKAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypePackageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.databrowser.MetadataType.CLASS);
    return mdClassIF.definesAttribute(TYPEPACKAGE);
  }
  
  public void setTypePackage(String value)
  {
    if(value == null)
    {
      setValue(TYPEPACKAGE, "");
    }
    else
    {
      setValue(TYPEPACKAGE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MetadataType get(String id)
  {
    return (MetadataType) com.runwaysdk.business.View.get(id);
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
