package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = 1620203608)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HasThematicLayer.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class HasThematicLayerQueryDTO extends com.runwaysdk.business.RelationshipQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1620203608;

  protected HasThematicLayerQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.HasThematicLayerDTO> getResultSet()
{
  return (java.util.List<? extends com.runwaysdk.geodashboard.gis.persist.HasThematicLayerDTO>)super.getResultSet();
}
}