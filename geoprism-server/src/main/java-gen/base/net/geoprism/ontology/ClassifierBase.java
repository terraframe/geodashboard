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
package net.geoprism.ontology;

@com.runwaysdk.business.ClassSignature(hash = 1891724562)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Classifier.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ClassifierBase extends com.runwaysdk.business.ontology.Term implements com.runwaysdk.generation.loader.Reloadable
{
  private static final com.runwaysdk.business.ontology.OntologyStrategyIF strategy;
  static 
  {
    strategy =  com.runwaysdk.business.ontology.Term.assignStrategy("net.geoprism.ontology.Classifier");
  }
  public final static String CLASS = "net.geoprism.ontology.Classifier";
  public static java.lang.String CLASSIFIERID = "classifierId";
  public static java.lang.String CLASSIFIERPACKAGE = "classifierPackage";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  private com.runwaysdk.business.Struct displayLabel = null;
  
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MANAGED = "managed";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1891724562;
  
  public ClassifierBase()
  {
    super();
    displayLabel = super.getStruct("displayLabel");
  }
  
  public String getClassifierId()
  {
    return getValue(CLASSIFIERID);
  }
  
  public void validateClassifierId()
  {
    this.validateAttribute(CLASSIFIERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getClassifierIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CLASSIFIERID);
  }
  
  public void setClassifierId(String value)
  {
    if(value == null)
    {
      setValue(CLASSIFIERID, "");
    }
    else
    {
      setValue(CLASSIFIERID, value);
    }
  }
  
  public String getClassifierPackage()
  {
    return getValue(CLASSIFIERPACKAGE);
  }
  
  public void validateClassifierPackage()
  {
    this.validateAttribute(CLASSIFIERPACKAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getClassifierPackageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CLASSIFIERPACKAGE);
  }
  
  public void setClassifierPackage(String value)
  {
    if(value == null)
    {
      setValue(CLASSIFIERPACKAGE, "");
    }
    else
    {
      setValue(CLASSIFIERPACKAGE, value);
    }
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public net.geoprism.ontology.ClassifierDisplayLabel getDisplayLabel()
  {
    return (net.geoprism.ontology.ClassifierDisplayLabel) displayLabel;
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF)mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
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
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Boolean getManaged()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(MANAGED));
  }
  
  public void validateManaged()
  {
    this.validateAttribute(MANAGED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getManagedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(MANAGED);
  }
  
  public void setManaged(Boolean value)
  {
    if(value == null)
    {
      setValue(MANAGED, "");
    }
    else
    {
      setValue(MANAGED, java.lang.Boolean.toString(value));
    }
  }
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.ontology.Classifier.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ClassifierQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ClassifierQuery query = new ClassifierQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public net.geoprism.ontology.ClassifierHasSynonym addHasSynonym(net.geoprism.ontology.ClassifierSynonym classifierSynonym)
  {
    return (net.geoprism.ontology.ClassifierHasSynonym) addChild(classifierSynonym, net.geoprism.ontology.ClassifierHasSynonym.CLASS);
  }
  
  public void removeHasSynonym(net.geoprism.ontology.ClassifierSynonym classifierSynonym)
  {
    removeAllChildren(classifierSynonym, net.geoprism.ontology.ClassifierHasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierSynonym> getAllHasSynonym()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierSynonym>) getChildren(net.geoprism.ontology.ClassifierHasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierHasSynonym> getAllHasSynonymRel()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierHasSynonym>) getChildRelationships(net.geoprism.ontology.ClassifierHasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public net.geoprism.ontology.ClassifierHasSynonym getHasSynonymRel(net.geoprism.ontology.ClassifierSynonym classifierSynonym)
  {
    com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierHasSynonym> iterator = (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierHasSynonym>) getRelationshipsWithChild(classifierSynonym, net.geoprism.ontology.ClassifierHasSynonym.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public net.geoprism.ontology.ClassifierIsARelationship addIsAChild(net.geoprism.ontology.Classifier classifier)
  {
    return (net.geoprism.ontology.ClassifierIsARelationship) addChild(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  public void removeIsAChild(net.geoprism.ontology.Classifier classifier)
  {
    removeAllChildren(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.Classifier> getAllIsAChild()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.Classifier>) getChildren(net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship> getAllIsAChildRel()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship>) getChildRelationships(net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public net.geoprism.ontology.ClassifierIsARelationship getIsAChildRel(net.geoprism.ontology.Classifier classifier)
  {
    com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship> iterator = (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship>) getRelationshipsWithChild(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public net.geoprism.ontology.ClassifierMultiTermAttributeRoot addClassifierMultiTermAttributeRoots(com.runwaysdk.system.metadata.MdAttributeMultiTerm mdAttributeMultiTerm)
  {
    return (net.geoprism.ontology.ClassifierMultiTermAttributeRoot) addParent(mdAttributeMultiTerm, net.geoprism.ontology.ClassifierMultiTermAttributeRoot.CLASS);
  }
  
  public void removeClassifierMultiTermAttributeRoots(com.runwaysdk.system.metadata.MdAttributeMultiTerm mdAttributeMultiTerm)
  {
    removeAllParents(mdAttributeMultiTerm, net.geoprism.ontology.ClassifierMultiTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends com.runwaysdk.system.metadata.MdAttributeMultiTerm> getAllClassifierMultiTermAttributeRoots()
  {
    return (com.runwaysdk.query.OIterator<? extends com.runwaysdk.system.metadata.MdAttributeMultiTerm>) getParents(net.geoprism.ontology.ClassifierMultiTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierMultiTermAttributeRoot> getAllClassifierMultiTermAttributeRootsRel()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierMultiTermAttributeRoot>) getParentRelationships(net.geoprism.ontology.ClassifierMultiTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierMultiTermAttributeRoot> getClassifierMultiTermAttributeRootsRel(com.runwaysdk.system.metadata.MdAttributeMultiTerm mdAttributeMultiTerm)
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierMultiTermAttributeRoot>) getRelationshipsWithParent(mdAttributeMultiTerm, net.geoprism.ontology.ClassifierMultiTermAttributeRoot.CLASS);
  }
  
  public net.geoprism.ontology.ClassifierTermAttributeRoot addClassifierTermAttributeRoots(com.runwaysdk.system.metadata.MdAttributeTerm mdAttributeTerm)
  {
    return (net.geoprism.ontology.ClassifierTermAttributeRoot) addParent(mdAttributeTerm, net.geoprism.ontology.ClassifierTermAttributeRoot.CLASS);
  }
  
  public void removeClassifierTermAttributeRoots(com.runwaysdk.system.metadata.MdAttributeTerm mdAttributeTerm)
  {
    removeAllParents(mdAttributeTerm, net.geoprism.ontology.ClassifierTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends com.runwaysdk.system.metadata.MdAttributeTerm> getAllClassifierTermAttributeRoots()
  {
    return (com.runwaysdk.query.OIterator<? extends com.runwaysdk.system.metadata.MdAttributeTerm>) getParents(net.geoprism.ontology.ClassifierTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierTermAttributeRoot> getAllClassifierTermAttributeRootsRel()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierTermAttributeRoot>) getParentRelationships(net.geoprism.ontology.ClassifierTermAttributeRoot.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierTermAttributeRoot> getClassifierTermAttributeRootsRel(com.runwaysdk.system.metadata.MdAttributeTerm mdAttributeTerm)
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierTermAttributeRoot>) getRelationshipsWithParent(mdAttributeTerm, net.geoprism.ontology.ClassifierTermAttributeRoot.CLASS);
  }
  
  public net.geoprism.ontology.ClassifierIsARelationship addIsAParent(net.geoprism.ontology.Classifier classifier)
  {
    return (net.geoprism.ontology.ClassifierIsARelationship) addParent(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  public void removeIsAParent(net.geoprism.ontology.Classifier classifier)
  {
    removeAllParents(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.Classifier> getAllIsAParent()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.Classifier>) getParents(net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship> getAllIsAParentRel()
  {
    return (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship>) getParentRelationships(net.geoprism.ontology.ClassifierIsARelationship.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public net.geoprism.ontology.ClassifierIsARelationship getIsAParentRel(net.geoprism.ontology.Classifier classifier)
  {
    com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship> iterator = (com.runwaysdk.query.OIterator<? extends net.geoprism.ontology.ClassifierIsARelationship>) getRelationshipsWithParent(classifier, net.geoprism.ontology.ClassifierIsARelationship.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static Classifier get(String id)
  {
    return (Classifier) com.runwaysdk.business.Business.get(id);
  }
  
  public static Classifier getByKey(String key)
  {
    return (Classifier) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static com.runwaysdk.business.ontology.OntologyStrategyIF getStrategy()
  {
    return strategy;
  }
  
  public static com.runwaysdk.business.ontology.TermAndRel create(net.geoprism.ontology.Classifier dto, java.lang.String parentId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteClassifierProblem(java.lang.String problemId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static net.geoprism.ontology.ClassifierProblemView[] getAllProblems()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.ValueQuery getClassifierSuggestions(java.lang.String mdAttributeId, java.lang.String text, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getClassifierTree(java.lang.String classifierId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getManagedClassifiersAsJSON()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static net.geoprism.ontology.Classifier getRoot()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String[] makeSynonym(java.lang.String sourceId, java.lang.String destinationId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String[] restoreSynonym(java.lang.String synonymId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.ontology.Classifier.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static Classifier lock(java.lang.String id)
  {
    Classifier _instance = Classifier.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Classifier unlock(java.lang.String id)
  {
    Classifier _instance = Classifier.get(id);
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
