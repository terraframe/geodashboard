package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = 1122144300)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LayerType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class LayerTypeQueryDTO extends com.runwaysdk.system.EnumerationMasterQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1122144300;

  protected LayerTypeQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO> getResultSet()
{
  return (java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO>)super.getResultSet();
}
}
