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
package net.geoprism.dashboard;

@com.runwaysdk.business.ClassSignature(hash = 1488949388)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardLegend.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardLegendBase extends com.runwaysdk.business.Struct implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.dashboard.DashboardLegend";
  public static java.lang.String GROUPEDINLEGEND = "groupedInLegend";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LEGENDXPOSITION = "legendXPosition";
  public static java.lang.String LEGENDYPOSITION = "legendYPosition";
  public static java.lang.String SITEMASTER = "siteMaster";
  private static final long serialVersionUID = 1488949388;
  
  public DashboardLegendBase()
  {
    super();
  }
  
  public DashboardLegendBase(com.runwaysdk.business.MutableWithStructs component, String structName)
  {
    super(component, structName);
  }
  
  public static DashboardLegend get(String id)
  {
    return (DashboardLegend) com.runwaysdk.business.Struct.get(id);
  }
  
  public static DashboardLegend getByKey(String key)
  {
    return (DashboardLegend) com.runwaysdk.business.Struct.get(CLASS, key);
  }
  
  public Boolean getGroupedInLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(GROUPEDINLEGEND));
  }
  
  public void validateGroupedInLegend()
  {
    this.validateAttribute(GROUPEDINLEGEND);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getGroupedInLegendMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(GROUPEDINLEGEND);
  }
  
  public void setGroupedInLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(GROUPEDINLEGEND, "");
    }
    else
    {
      setValue(GROUPEDINLEGEND, java.lang.Boolean.toString(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public Integer getLegendXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDXPOSITION));
  }
  
  public void validateLegendXPosition()
  {
    this.validateAttribute(LEGENDXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getLegendXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(LEGENDXPOSITION);
  }
  
  public void setLegendXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDXPOSITION, "");
    }
    else
    {
      setValue(LEGENDXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getLegendYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDYPOSITION));
  }
  
  public void validateLegendYPosition()
  {
    this.validateAttribute(LEGENDYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getLegendYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(LEGENDYPOSITION);
  }
  
  public void setLegendYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDYPOSITION, "");
    }
    else
    {
      setValue(LEGENDYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.dashboard.DashboardLegend.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardLegendQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardLegendQuery query = new DashboardLegendQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
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