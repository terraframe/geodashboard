package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = -300460696)

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardLayer.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class DashboardLayerQueryDTO extends com.runwaysdk.business.BusinessQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -300460696;

  protected DashboardLayerQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.DashboardLayerDTO> getResultSet()
{
  return (java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.DashboardLayerDTO>)super.getResultSet();
}
}
