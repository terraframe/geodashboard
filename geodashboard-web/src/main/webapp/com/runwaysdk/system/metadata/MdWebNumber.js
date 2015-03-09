Mojo.Meta.newClass('com.runwaysdk.system.metadata.Metadata', {
  Extends : 'com.runwaysdk.business.BusinessDTO',
  IsAbstract : true,
  Constants : 
  {
    CREATEDATE : 'createDate',
    CREATEDBY : 'createdBy',
    DESCRIPTION : 'description',
    ENTITYDOMAIN : 'entityDomain',
    ID : 'id',
    KEYNAME : 'keyName',
    LASTUPDATEDATE : 'lastUpdateDate',
    LASTUPDATEDBY : 'lastUpdatedBy',
    LOCKEDBY : 'lockedBy',
    OWNER : 'owner',
    REMOVE : 'remove',
    SEQ : 'seq',
    SITEMASTER : 'siteMaster',
    TYPE : 'type',
    CLASS : 'com.runwaysdk.system.metadata.Metadata'
  },
  Instance: 
  {
    getCreateDate : function()
    {
      return this.getAttributeDTO('createDate').getValue();
    },
    isCreateDateReadable : function()
    {
      return this.getAttributeDTO('createDate').isReadable();
    },
    isCreateDateWritable : function()
    {
      return this.getAttributeDTO('createDate').isWritable();
    },
    isCreateDateModified : function()
    {
      return this.getAttributeDTO('createDate').isModified();
    },
    getCreateDateMd : function()
    {
      return this.getAttributeDTO('createDate').getAttributeMdDTO();
    },
    getCreatedBy : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('createdBy');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    isCreatedByReadable : function()
    {
      return this.getAttributeDTO('createdBy').isReadable();
    },
    isCreatedByWritable : function()
    {
      return this.getAttributeDTO('createdBy').isWritable();
    },
    isCreatedByModified : function()
    {
      return this.getAttributeDTO('createdBy').isModified();
    },
    getCreatedByMd : function()
    {
      return this.getAttributeDTO('createdBy').getAttributeMdDTO();
    },
    getDescription : function()
    {
      if(Mojo.Meta.classExists('com.runwaysdk.system.metadata.MetadataDisplayLabel'))
      {
        var structDTO = this.getAttributeDTO('description').getStructDTO();
        if(structDTO == null)
        {
          return null;
        }
        else if(structDTO instanceof Mojo.$.com.runwaysdk.system.metadata.MetadataDisplayLabel)
        {
          return structDTO;
        }
        else
        {
          structDTO = new Mojo.$.com.runwaysdk.system.metadata.MetadataDisplayLabel(structDTO);
          this.getAttributeDTO('description').setStructDTO(structDTO);
          return structDTO;
        }
      }
      else
      {
        throw new Mojo.$.com.runwaysdk.Exception('Must import type com.runwaysdk.system.metadata.MetadataDisplayLabel');
      }
    },
    isDescriptionReadable : function()
    {
      return this.getAttributeDTO('description').isReadable();
    },
    isDescriptionWritable : function()
    {
      return this.getAttributeDTO('description').isWritable();
    },
    isDescriptionModified : function()
    {
      return this.getAttributeDTO('description').isModified();
    },
    getDescriptionMd : function()
    {
      return this.getAttributeDTO('description').getAttributeMdDTO();
    },
    getEntityDomain : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('entityDomain');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    setEntityDomain : function(ref)
    {
      var attributeDTO = this.getAttributeDTO('entityDomain');
      attributeDTO.setValue(Mojo.Util.isObject(ref) ? ref.getId() : ref);
      this.setModified(true);
    },
    isEntityDomainReadable : function()
    {
      return this.getAttributeDTO('entityDomain').isReadable();
    },
    isEntityDomainWritable : function()
    {
      return this.getAttributeDTO('entityDomain').isWritable();
    },
    isEntityDomainModified : function()
    {
      return this.getAttributeDTO('entityDomain').isModified();
    },
    getEntityDomainMd : function()
    {
      return this.getAttributeDTO('entityDomain').getAttributeMdDTO();
    },
    getKeyName : function()
    {
      return this.getAttributeDTO('keyName').getValue();
    },
    setKeyName : function(value)
    {
      var attributeDTO = this.getAttributeDTO('keyName');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isKeyNameReadable : function()
    {
      return this.getAttributeDTO('keyName').isReadable();
    },
    isKeyNameWritable : function()
    {
      return this.getAttributeDTO('keyName').isWritable();
    },
    isKeyNameModified : function()
    {
      return this.getAttributeDTO('keyName').isModified();
    },
    getKeyNameMd : function()
    {
      return this.getAttributeDTO('keyName').getAttributeMdDTO();
    },
    getLastUpdateDate : function()
    {
      return this.getAttributeDTO('lastUpdateDate').getValue();
    },
    isLastUpdateDateReadable : function()
    {
      return this.getAttributeDTO('lastUpdateDate').isReadable();
    },
    isLastUpdateDateWritable : function()
    {
      return this.getAttributeDTO('lastUpdateDate').isWritable();
    },
    isLastUpdateDateModified : function()
    {
      return this.getAttributeDTO('lastUpdateDate').isModified();
    },
    getLastUpdateDateMd : function()
    {
      return this.getAttributeDTO('lastUpdateDate').getAttributeMdDTO();
    },
    getLastUpdatedBy : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('lastUpdatedBy');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    isLastUpdatedByReadable : function()
    {
      return this.getAttributeDTO('lastUpdatedBy').isReadable();
    },
    isLastUpdatedByWritable : function()
    {
      return this.getAttributeDTO('lastUpdatedBy').isWritable();
    },
    isLastUpdatedByModified : function()
    {
      return this.getAttributeDTO('lastUpdatedBy').isModified();
    },
    getLastUpdatedByMd : function()
    {
      return this.getAttributeDTO('lastUpdatedBy').getAttributeMdDTO();
    },
    getLockedBy : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('lockedBy');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    isLockedByReadable : function()
    {
      return this.getAttributeDTO('lockedBy').isReadable();
    },
    isLockedByWritable : function()
    {
      return this.getAttributeDTO('lockedBy').isWritable();
    },
    isLockedByModified : function()
    {
      return this.getAttributeDTO('lockedBy').isModified();
    },
    getLockedByMd : function()
    {
      return this.getAttributeDTO('lockedBy').getAttributeMdDTO();
    },
    getOwner : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('owner');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    setOwner : function(ref)
    {
      var attributeDTO = this.getAttributeDTO('owner');
      attributeDTO.setValue(Mojo.Util.isObject(ref) ? ref.getId() : ref);
      this.setModified(true);
    },
    isOwnerReadable : function()
    {
      return this.getAttributeDTO('owner').isReadable();
    },
    isOwnerWritable : function()
    {
      return this.getAttributeDTO('owner').isWritable();
    },
    isOwnerModified : function()
    {
      return this.getAttributeDTO('owner').isModified();
    },
    getOwnerMd : function()
    {
      return this.getAttributeDTO('owner').getAttributeMdDTO();
    },
    getRemove : function()
    {
      return this.getAttributeDTO('remove').getValue();
    },
    setRemove : function(value)
    {
      var attributeDTO = this.getAttributeDTO('remove');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isRemoveReadable : function()
    {
      return this.getAttributeDTO('remove').isReadable();
    },
    isRemoveWritable : function()
    {
      return this.getAttributeDTO('remove').isWritable();
    },
    isRemoveModified : function()
    {
      return this.getAttributeDTO('remove').isModified();
    },
    getRemoveMd : function()
    {
      return this.getAttributeDTO('remove').getAttributeMdDTO();
    },
    getSeq : function()
    {
      return this.getAttributeDTO('seq').getValue();
    },
    isSeqReadable : function()
    {
      return this.getAttributeDTO('seq').isReadable();
    },
    isSeqWritable : function()
    {
      return this.getAttributeDTO('seq').isWritable();
    },
    isSeqModified : function()
    {
      return this.getAttributeDTO('seq').isModified();
    },
    getSeqMd : function()
    {
      return this.getAttributeDTO('seq').getAttributeMdDTO();
    },
    getSiteMaster : function()
    {
      return this.getAttributeDTO('siteMaster').getValue();
    },
    isSiteMasterReadable : function()
    {
      return this.getAttributeDTO('siteMaster').isReadable();
    },
    isSiteMasterWritable : function()
    {
      return this.getAttributeDTO('siteMaster').isWritable();
    },
    isSiteMasterModified : function()
    {
      return this.getAttributeDTO('siteMaster').isModified();
    },
    getSiteMasterMd : function()
    {
      return this.getAttributeDTO('siteMaster').getAttributeMdDTO();
    },
    getAllActor : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParents(clientRequest, this.getId(), 'com.runwaysdk.system.TypePermission');
    },
    getAllActorRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParentRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.TypePermission');
    },
    addActor : function(clientRequest, parent)
    {
      var parentId = (parent instanceof Object) ? parent.getId() : parent;
      Mojo.$.com.runwaysdk.Facade.addParent(clientRequest, parentId, this.getId(), 'com.runwaysdk.system.TypePermission');
    },
    removeActor : function(clientRequest, relationship)
    {
      var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteParent(clientRequest, relationshipId);
    },
    removeAllActor : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteParents(clientRequest, this.getId(), 'com.runwaysdk.system.TypePermission');
    },
    getAllChildMetadata : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getChildren(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.MetadataRelationship');
    },
    getAllChildMetadataRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getChildRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.MetadataRelationship');
    },
    addChildMetadata : function(clientRequest, child)
    {
      var childId = (child instanceof Object) ? child.getId() : child;
      Mojo.$.com.runwaysdk.Facade.addChild(clientRequest, this.getId(), childId, 'com.runwaysdk.system.metadata.MetadataRelationship');
    },
    removeChildMetadata : function(clientRequest, relationship)
    {
        var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteChild(clientRequest, relationshipId);
    },
    removeAllChildMetadata : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteChildren(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.MetadataRelationship');
    },
    getAllMdParameter : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getChildren(clientRequest, this.getId(), 'com.runwaysdk.system.MetadataParameter');
    },
    getAllMdParameterRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getChildRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.MetadataParameter');
    },
    addMdParameter : function(clientRequest, child)
    {
      var childId = (child instanceof Object) ? child.getId() : child;
      Mojo.$.com.runwaysdk.Facade.addChild(clientRequest, this.getId(), childId, 'com.runwaysdk.system.MetadataParameter');
    },
    removeMdParameter : function(clientRequest, relationship)
    {
        var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteChild(clientRequest, relationshipId);
    },
    removeAllMdParameter : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteChildren(clientRequest, this.getId(), 'com.runwaysdk.system.MetadataParameter');
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MetadataQueryDTO', {
  Extends : 'com.runwaysdk.business.BusinessQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.Metadata'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdField', {
  Extends : 'com.runwaysdk.system.metadata.Metadata',
  IsAbstract : true,
  Constants : 
  {
    DISPLAYLABEL : 'displayLabel',
    FIELDCONDITION : 'fieldCondition',
    FIELDNAME : 'fieldName',
    FIELDORDER : 'fieldOrder',
    REQUIRED : 'required',
    CLASS : 'com.runwaysdk.system.metadata.MdField'
  },
  Instance: 
  {
    getDisplayLabel : function()
    {
      if(Mojo.Meta.classExists('com.runwaysdk.system.metadata.MdFieldDisplayLabel'))
      {
        var structDTO = this.getAttributeDTO('displayLabel').getStructDTO();
        if(structDTO == null)
        {
          return null;
        }
        else if(structDTO instanceof Mojo.$.com.runwaysdk.system.metadata.MdFieldDisplayLabel)
        {
          return structDTO;
        }
        else
        {
          structDTO = new Mojo.$.com.runwaysdk.system.metadata.MdFieldDisplayLabel(structDTO);
          this.getAttributeDTO('displayLabel').setStructDTO(structDTO);
          return structDTO;
        }
      }
      else
      {
        throw new Mojo.$.com.runwaysdk.Exception('Must import type com.runwaysdk.system.metadata.MdFieldDisplayLabel');
      }
    },
    isDisplayLabelReadable : function()
    {
      return this.getAttributeDTO('displayLabel').isReadable();
    },
    isDisplayLabelWritable : function()
    {
      return this.getAttributeDTO('displayLabel').isWritable();
    },
    isDisplayLabelModified : function()
    {
      return this.getAttributeDTO('displayLabel').isModified();
    },
    getDisplayLabelMd : function()
    {
      return this.getAttributeDTO('displayLabel').getAttributeMdDTO();
    },
    getFieldCondition : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('fieldCondition');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    setFieldCondition : function(ref)
    {
      var attributeDTO = this.getAttributeDTO('fieldCondition');
      attributeDTO.setValue(Mojo.Util.isObject(ref) ? ref.getId() : ref);
      this.setModified(true);
    },
    isFieldConditionReadable : function()
    {
      return this.getAttributeDTO('fieldCondition').isReadable();
    },
    isFieldConditionWritable : function()
    {
      return this.getAttributeDTO('fieldCondition').isWritable();
    },
    isFieldConditionModified : function()
    {
      return this.getAttributeDTO('fieldCondition').isModified();
    },
    getFieldConditionMd : function()
    {
      return this.getAttributeDTO('fieldCondition').getAttributeMdDTO();
    },
    getFieldName : function()
    {
      return this.getAttributeDTO('fieldName').getValue();
    },
    setFieldName : function(value)
    {
      var attributeDTO = this.getAttributeDTO('fieldName');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isFieldNameReadable : function()
    {
      return this.getAttributeDTO('fieldName').isReadable();
    },
    isFieldNameWritable : function()
    {
      return this.getAttributeDTO('fieldName').isWritable();
    },
    isFieldNameModified : function()
    {
      return this.getAttributeDTO('fieldName').isModified();
    },
    getFieldNameMd : function()
    {
      return this.getAttributeDTO('fieldName').getAttributeMdDTO();
    },
    getFieldOrder : function()
    {
      return this.getAttributeDTO('fieldOrder').getValue();
    },
    setFieldOrder : function(value)
    {
      var attributeDTO = this.getAttributeDTO('fieldOrder');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isFieldOrderReadable : function()
    {
      return this.getAttributeDTO('fieldOrder').isReadable();
    },
    isFieldOrderWritable : function()
    {
      return this.getAttributeDTO('fieldOrder').isWritable();
    },
    isFieldOrderModified : function()
    {
      return this.getAttributeDTO('fieldOrder').isModified();
    },
    getFieldOrderMd : function()
    {
      return this.getAttributeDTO('fieldOrder').getAttributeMdDTO();
    },
    getRequired : function()
    {
      return this.getAttributeDTO('required').getValue();
    },
    setRequired : function(value)
    {
      var attributeDTO = this.getAttributeDTO('required');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isRequiredReadable : function()
    {
      return this.getAttributeDTO('required').isReadable();
    },
    isRequiredWritable : function()
    {
      return this.getAttributeDTO('required').isWritable();
    },
    isRequiredModified : function()
    {
      return this.getAttributeDTO('required').isModified();
    },
    getRequiredMd : function()
    {
      return this.getAttributeDTO('required').getAttributeMdDTO();
    },
    getAllMdForm : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.FormField');
    },
    getAllMdFormRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParentRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.FormField');
    },
    addMdForm : function(clientRequest, parent)
    {
      var parentId = (parent instanceof Object) ? parent.getId() : parent;
      Mojo.$.com.runwaysdk.Facade.addParent(clientRequest, parentId, this.getId(), 'com.runwaysdk.system.metadata.FormField');
    },
    removeMdForm : function(clientRequest, relationship)
    {
      var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteParent(clientRequest, relationshipId);
    },
    removeAllMdForm : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.FormField');
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdFieldQueryDTO', {
  Extends : 'com.runwaysdk.system.metadata.MetadataQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.MdField'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebField', {
  Extends : 'com.runwaysdk.system.metadata.MdField',
  IsAbstract : true,
  Constants : 
  {
    DEFININGMDFORM : 'definingMdForm',
    CLASS : 'com.runwaysdk.system.metadata.MdWebField'
  },
  Instance: 
  {
    getDefiningMdForm : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('definingMdForm');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    setDefiningMdForm : function(ref)
    {
      var attributeDTO = this.getAttributeDTO('definingMdForm');
      attributeDTO.setValue(Mojo.Util.isObject(ref) ? ref.getId() : ref);
      this.setModified(true);
    },
    isDefiningMdFormReadable : function()
    {
      return this.getAttributeDTO('definingMdForm').isReadable();
    },
    isDefiningMdFormWritable : function()
    {
      return this.getAttributeDTO('definingMdForm').isWritable();
    },
    isDefiningMdFormModified : function()
    {
      return this.getAttributeDTO('definingMdForm').isModified();
    },
    getDefiningMdFormMd : function()
    {
      return this.getAttributeDTO('definingMdForm').getAttributeMdDTO();
    },
    getAllGroupFields : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGroupField');
    },
    getAllGroupFieldsRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParentRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGroupField');
    },
    addGroupFields : function(clientRequest, parent)
    {
      var parentId = (parent instanceof Object) ? parent.getId() : parent;
      Mojo.$.com.runwaysdk.Facade.addParent(clientRequest, parentId, this.getId(), 'com.runwaysdk.system.metadata.WebGroupField');
    },
    removeGroupFields : function(clientRequest, relationship)
    {
      var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteParent(clientRequest, relationshipId);
    },
    removeAllGroupFields : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGroupField');
    },
    getAllMdForm : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebFormField');
    },
    getAllMdFormRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParentRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebFormField');
    },
    addMdForm : function(clientRequest, parent)
    {
      var parentId = (parent instanceof Object) ? parent.getId() : parent;
      Mojo.$.com.runwaysdk.Facade.addParent(clientRequest, parentId, this.getId(), 'com.runwaysdk.system.metadata.WebFormField');
    },
    removeMdForm : function(clientRequest, relationship)
    {
      var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteParent(clientRequest, relationshipId);
    },
    removeAllMdForm : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebFormField');
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebFieldQueryDTO', {
  Extends : 'com.runwaysdk.system.metadata.MdFieldQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.MdWebField'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebAttribute', {
  Extends : 'com.runwaysdk.system.metadata.MdWebField',
  IsAbstract : true,
  Constants : 
  {
    DEFININGMDATTRIBUTE : 'definingMdAttribute',
    SHOWONSEARCH : 'showOnSearch',
    SHOWONVIEWALL : 'showOnViewAll',
    CLASS : 'com.runwaysdk.system.metadata.MdWebAttribute'
  },
  Instance: 
  {
    getDefiningMdAttribute : function(clientRequest)
    {
      var attributeDTO = this.getAttributeDTO('definingMdAttribute');
      var value = attributeDTO.getValue();
      if(value == null || value == '')
      {
        clientRequest.onSuccess(null);
      }
      else
      {
        Mojo.$.com.runwaysdk.Facade.get(clientRequest, value);
      }
    },
    setDefiningMdAttribute : function(ref)
    {
      var attributeDTO = this.getAttributeDTO('definingMdAttribute');
      attributeDTO.setValue(Mojo.Util.isObject(ref) ? ref.getId() : ref);
      this.setModified(true);
    },
    isDefiningMdAttributeReadable : function()
    {
      return this.getAttributeDTO('definingMdAttribute').isReadable();
    },
    isDefiningMdAttributeWritable : function()
    {
      return this.getAttributeDTO('definingMdAttribute').isWritable();
    },
    isDefiningMdAttributeModified : function()
    {
      return this.getAttributeDTO('definingMdAttribute').isModified();
    },
    getDefiningMdAttributeMd : function()
    {
      return this.getAttributeDTO('definingMdAttribute').getAttributeMdDTO();
    },
    getShowOnSearch : function()
    {
      return this.getAttributeDTO('showOnSearch').getValue();
    },
    setShowOnSearch : function(value)
    {
      var attributeDTO = this.getAttributeDTO('showOnSearch');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isShowOnSearchReadable : function()
    {
      return this.getAttributeDTO('showOnSearch').isReadable();
    },
    isShowOnSearchWritable : function()
    {
      return this.getAttributeDTO('showOnSearch').isWritable();
    },
    isShowOnSearchModified : function()
    {
      return this.getAttributeDTO('showOnSearch').isModified();
    },
    getShowOnSearchMd : function()
    {
      return this.getAttributeDTO('showOnSearch').getAttributeMdDTO();
    },
    getShowOnViewAll : function()
    {
      return this.getAttributeDTO('showOnViewAll').getValue();
    },
    setShowOnViewAll : function(value)
    {
      var attributeDTO = this.getAttributeDTO('showOnViewAll');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isShowOnViewAllReadable : function()
    {
      return this.getAttributeDTO('showOnViewAll').isReadable();
    },
    isShowOnViewAllWritable : function()
    {
      return this.getAttributeDTO('showOnViewAll').isWritable();
    },
    isShowOnViewAllModified : function()
    {
      return this.getAttributeDTO('showOnViewAll').isModified();
    },
    getShowOnViewAllMd : function()
    {
      return this.getAttributeDTO('showOnViewAll').getAttributeMdDTO();
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebAttributeQueryDTO', {
  Extends : 'com.runwaysdk.system.metadata.MdWebFieldQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.MdWebAttribute'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebPrimitive', {
  Extends : 'com.runwaysdk.system.metadata.MdWebAttribute',
  IsAbstract : true,
  Constants : 
  {
    EXPRESSION : 'expression',
    ISEXPRESSION : 'isExpression',
    CLASS : 'com.runwaysdk.system.metadata.MdWebPrimitive'
  },
  Instance: 
  {
    getExpression : function()
    {
      return this.getAttributeDTO('expression').getValue();
    },
    setExpression : function(value)
    {
      var attributeDTO = this.getAttributeDTO('expression');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isExpressionReadable : function()
    {
      return this.getAttributeDTO('expression').isReadable();
    },
    isExpressionWritable : function()
    {
      return this.getAttributeDTO('expression').isWritable();
    },
    isExpressionModified : function()
    {
      return this.getAttributeDTO('expression').isModified();
    },
    getExpressionMd : function()
    {
      return this.getAttributeDTO('expression').getAttributeMdDTO();
    },
    getIsExpression : function()
    {
      return this.getAttributeDTO('isExpression').getValue();
    },
    setIsExpression : function(value)
    {
      var attributeDTO = this.getAttributeDTO('isExpression');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isIsExpressionReadable : function()
    {
      return this.getAttributeDTO('isExpression').isReadable();
    },
    isIsExpressionWritable : function()
    {
      return this.getAttributeDTO('isExpression').isWritable();
    },
    isIsExpressionModified : function()
    {
      return this.getAttributeDTO('isExpression').isModified();
    },
    getIsExpressionMd : function()
    {
      return this.getAttributeDTO('isExpression').getAttributeMdDTO();
    },
    getAllGrid : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGridField');
    },
    getAllGridRelationships : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.getParentRelationships(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGridField');
    },
    addGrid : function(clientRequest, parent)
    {
      var parentId = (parent instanceof Object) ? parent.getId() : parent;
      Mojo.$.com.runwaysdk.Facade.addParent(clientRequest, parentId, this.getId(), 'com.runwaysdk.system.metadata.WebGridField');
    },
    removeGrid : function(clientRequest, relationship)
    {
      var relationshipId = (relationship instanceof Object) ? relationship.getId() : relationship;
      Mojo.$.com.runwaysdk.Facade.deleteParent(clientRequest, relationshipId);
    },
    removeAllGrid : function(clientRequest)
    {
      Mojo.$.com.runwaysdk.Facade.deleteParents(clientRequest, this.getId(), 'com.runwaysdk.system.metadata.WebGridField');
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebPrimitiveQueryDTO', {
  Extends : 'com.runwaysdk.system.metadata.MdWebAttributeQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.MdWebPrimitive'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebNumber', {
  Extends : 'com.runwaysdk.system.metadata.MdWebPrimitive',
  IsAbstract : true,
  Constants : 
  {
    ENDRANGE : 'endRange',
    STARTRANGE : 'startRange',
    CLASS : 'com.runwaysdk.system.metadata.MdWebNumber'
  },
  Instance: 
  {
    getEndRange : function()
    {
      return this.getAttributeDTO('endRange').getValue();
    },
    setEndRange : function(value)
    {
      var attributeDTO = this.getAttributeDTO('endRange');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isEndRangeReadable : function()
    {
      return this.getAttributeDTO('endRange').isReadable();
    },
    isEndRangeWritable : function()
    {
      return this.getAttributeDTO('endRange').isWritable();
    },
    isEndRangeModified : function()
    {
      return this.getAttributeDTO('endRange').isModified();
    },
    getEndRangeMd : function()
    {
      return this.getAttributeDTO('endRange').getAttributeMdDTO();
    },
    getStartRange : function()
    {
      return this.getAttributeDTO('startRange').getValue();
    },
    setStartRange : function(value)
    {
      var attributeDTO = this.getAttributeDTO('startRange');
      attributeDTO.setValue(value);
      this.setModified(true);
    },
    isStartRangeReadable : function()
    {
      return this.getAttributeDTO('startRange').isReadable();
    },
    isStartRangeWritable : function()
    {
      return this.getAttributeDTO('startRange').isWritable();
    },
    isStartRangeModified : function()
    {
      return this.getAttributeDTO('startRange').isModified();
    },
    getStartRangeMd : function()
    {
      return this.getAttributeDTO('startRange').getAttributeMdDTO();
    },
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
Mojo.Meta.newClass('com.runwaysdk.system.metadata.MdWebNumberQueryDTO', {
  Extends : 'com.runwaysdk.system.metadata.MdWebPrimitiveQueryDTO',
  Constants : 
  {
    CLASS : 'com.runwaysdk.system.metadata.MdWebNumber'
  },
  Instance: 
  {
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  },
  Static: 
  {
  }
});
