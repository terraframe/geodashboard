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
package net.geoprism.report;

@com.runwaysdk.business.ClassSignature(hash = 853827134)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PairView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PairViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.report.PairView";
  public static java.lang.String ID = "id";
  public static java.lang.String LABEL = "label";
  public static java.lang.String VALUE = "value";
  private static final long serialVersionUID = 853827134;
  
  public PairViewBase()
  {
    super();
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.report.PairView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getLabel()
  {
    return getValue(LABEL);
  }
  
  public void validateLabel()
  {
    this.validateAttribute(LABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.report.PairView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(LABEL);
  }
  
  public void setLabel(String value)
  {
    if(value == null)
    {
      setValue(LABEL, "");
    }
    else
    {
      setValue(LABEL, value);
    }
  }
  
  public String getValue()
  {
    return getValue(VALUE);
  }
  
  public void validateValue()
  {
    this.validateAttribute(VALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.report.PairView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(VALUE);
  }
  
  public void setValue(String value)
  {
    if(value == null)
    {
      setValue(VALUE, "");
    }
    else
    {
      setValue(VALUE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PairView get(String id)
  {
    return (PairView) com.runwaysdk.business.View.get(id);
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