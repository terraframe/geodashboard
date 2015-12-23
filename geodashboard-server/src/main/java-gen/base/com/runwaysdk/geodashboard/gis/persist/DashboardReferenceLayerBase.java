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
package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = -1328161896)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardReferenceLayer.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardReferenceLayerBase extends com.runwaysdk.geodashboard.gis.persist.DashboardLayer implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.DashboardReferenceLayer";
  public static java.lang.String UNIVERSAL = "universal";
  private static final long serialVersionUID = -1328161896;
  
  public DashboardReferenceLayerBase()
  {
    super();
  }
  
  public com.runwaysdk.system.gis.geo.Universal getUniversal()
  {
    if (getValue(UNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.gis.geo.Universal.get(getValue(UNIVERSAL));
    }
  }
  
  public String getUniversalId()
  {
    return getValue(UNIVERSAL);
  }
  
  public void validateUniversal()
  {
    this.validateAttribute(UNIVERSAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getUniversalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.DashboardReferenceLayer.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(UNIVERSAL);
  }
  
  public void setUniversal(com.runwaysdk.system.gis.geo.Universal value)
  {
    if(value == null)
    {
      setValue(UNIVERSAL, "");
    }
    else
    {
      setValue(UNIVERSAL, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardReferenceLayerQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardReferenceLayerQuery query = new DashboardReferenceLayerQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DashboardReferenceLayer get(String id)
  {
    return (DashboardReferenceLayer) com.runwaysdk.business.Business.get(id);
  }
  
  public static DashboardReferenceLayer getByKey(String key)
  {
    return (DashboardReferenceLayer) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static java.lang.String getOptionsJSON(java.lang.String dashboardId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.gis.persist.DashboardReferenceLayer.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static DashboardReferenceLayer lock(java.lang.String id)
  {
    DashboardReferenceLayer _instance = DashboardReferenceLayer.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DashboardReferenceLayer unlock(java.lang.String id)
  {
    DashboardReferenceLayer _instance = DashboardReferenceLayer.get(id);
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
