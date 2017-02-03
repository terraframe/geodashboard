package net.geoprism.ontology;

@com.runwaysdk.business.ClassSignature(hash = 1426692328)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoEntityUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.ontology.GeoEntityUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1426692328;
  
  public GeoEntityUtilBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.GeoEntityUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoEntityUtil get(String id)
  {
    return (GeoEntityUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static void deleteEntityProblem(java.lang.String problemId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.gis.geo.GeoEntityProblemView[] getAllProblems()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.ValueQuery getChildren(java.lang.String id, java.lang.String universalId, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream getData(java.lang.String config)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.gis.geo.GeoEntity getEntity(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getEntityLabel(java.lang.String entityId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.ValueQuery getGeoEntitySuggestions(java.lang.String parentId, java.lang.String universalId, java.lang.String text, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getGeoEntityTree(java.lang.String geoEntityId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.gis.geo.GeoEntity[] getOrderedAncestors(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String[] makeSynonym(java.lang.String sourceId, java.lang.String destinationId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String publishLayers(java.lang.String id, java.lang.String universalId, java.lang.String existingLayerNames)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void refreshViews(java.lang.String existingLayerNames)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String[] restoreSynonym(java.lang.String synonymId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.GeoEntityUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
