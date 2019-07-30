/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Geoprism(tm).
 *
 * Geoprism(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Geoprism(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Geoprism(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.dhis2.exporter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeNumber;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClass;

import net.geoprism.dhis2.DHIS2IdMapping;
import net.geoprism.dhis2.DHIS2IdMappingQuery;
import net.geoprism.dhis2.connector.AbstractDHIS2Connector;
import net.geoprism.dhis2.response.DHIS2EmptyDatasetException;
import net.geoprism.dhis2.response.DHIS2TrackerResponseProcessor;
import net.geoprism.dhis2.response.GeoFieldRequiredException;
import net.geoprism.dhis2.response.HTTPResponse;
import net.geoprism.dhis2.util.DHIS2IdCache;
import net.geoprism.dhis2.util.DHIS2Util;
import net.geoprism.ontology.Classifier;
import net.geoprism.ontology.ClassifierSynonym;



/**
 * 
 * http://clts2.dhis2.net/dhis/api/25/trackedEntityInstances?ou=PmcKpRzAf4B
 * http://clts2.dhis2.net/dhis/api/25/enrollments?ou=PmcKpRzAf4B
 * http://clts2.dhis2.net/dhis/api/25/events?ou=PmcKpRzAf4B&program=rDRZrQiZJnG
 * 
 * 
 * SNV waterpoint
 * 
 * 
 * 

event reports -> events




https://github.com/jembi/dhis2-tracker-populator



TODO : 

1. why does CLTS_Village need a attribute oid? (hint: it doesn't)
2. events
3. updating an existing option doesn't work, its assuming the code = oid (probably a problem with the importer, not the exporter)
4. [incident date] and [enrollment date] set to [report date]
 */



/**
 * This class is responsible for exporting an MdBusiness to DHIS2.
 * 
 * @precondition All of the Classifiers that are referenced by attributes have already been exported.
 * 
 * @author rrowlands
 */
public class MdBusinessExporter
{
  private static Logger logger = LoggerFactory.getLogger(MdBusinessExporter.class);
  
  protected MdBusiness mdbiz;
  
  protected MdBusinessToTrackerJson converter;
  
  protected AbstractDHIS2Connector dhis2;
  
  private String trackedEntityId;
  
  // Key is runway OID value is dhis2 oid
  private Map<String, String> trackedEntityAttributeIds;
  
  private DHIS2IdCache idCache;
  
//  private Map<String, String> programTrackedEntityAttributeIds;
  
  private final int pageSize = 10000;
  
  private long teiCount;
  
  static String[] skipAttrs = new String[]{
    MdBusinessDTO.CACHEALGORITHM, MdBusinessDTO.TABLENAME, MdBusinessDTO.KEYNAME,
    MdBusinessDTO.BASECLASS, MdBusinessDTO.BASESOURCE, MdBusinessDTO.DTOCLASS, MdBusinessDTO.DTOSOURCE, MdBusinessDTO.STUBCLASS, MdBusinessDTO.STUBDTOCLASS, MdBusinessDTO.STUBDTOSOURCE, MdBusinessDTO.STUBSOURCE,
    MdAttributeConcreteDTO.GETTERVISIBILITY, MdAttributeConcreteDTO.INDEXTYPE, MdAttributeConcreteDTO.INDEXNAME, MdAttributeConcreteDTO.COLUMNNAME,
    MdAttributeConcreteDTO.DEFININGMDCLASS, MdAttributeConcreteDTO.ENTITYDOMAIN, MdAttributeConcreteDTO.OWNER, MdAttributeConcreteDTO.SETTERVISIBILITY, MdAttributeConcreteDTO.SITEMASTER
  };
  
  public MdBusinessExporter(MdBusiness mdbiz, AbstractDHIS2Connector dhis2)
  {
    this.mdbiz = mdbiz;
    this.dhis2 = dhis2;
    this.idCache = new DHIS2IdCache(dhis2);
    this.converter = new MdBusinessToTrackerJson(mdbiz, idCache);
  }
  
  public void xport(MdClass mdClass)
  {
    
  }
  
  public void exportToTracker()
  {
    trackedEntityAttributeIds = new HashMap<String, String>();
    
    validateExport();
    
    QueryFactory qf = new QueryFactory();
    DHIS2IdMappingQuery mapQ = new DHIS2IdMappingQuery(qf);
    mapQ.WHERE(mapQ.getRunwayId().EQ(mdbiz.getOid()));
    long count = mapQ.getCount();
    
    if (count == 0) // CREATE NEW
    {
      createTrackedEntity();
      createTrackedEntityAttributes();
//      createProgramTrackedEntityAttributes(); // only works on 2.25
      createProgram();
    }
    else // UPDATE EXISTING
    {
      OIterator<? extends DHIS2IdMapping> it = mapQ.getIterator();
      DHIS2IdMapping mapping;
      try
      {
        mapping = it.next();
      }
      finally
      {
        it.close();
      }
      
      String[] split = mapping.getDhis2Id().split(":");
      
      trackedEntityId = split[1];
      converter.setTrackedEntityId(trackedEntityId);
      converter.setProgramId(split[0]);
      
      List<? extends MdAttribute> mdAttrs = mdbiz.getAllAttribute().getAll();
      for (MdAttribute mdAttr : mdAttrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(MdBusinessExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          )
        {
          QueryFactory qf2 = new QueryFactory();
          DHIS2IdMappingQuery mapQ2 = new DHIS2IdMappingQuery(qf2);
          mapQ2.WHERE(mapQ2.getRunwayId().EQ(mdAttr.getOid()));
          OIterator<? extends DHIS2IdMapping> it2 = mapQ2.getIterator();
          
          try
          {
            if (it2.hasNext())
            {
              DHIS2IdMapping attrMapping = it2.next();
              
              trackedEntityAttributeIds.put(attrMapping.getRunwayId(), attrMapping.getDhis2Id());
            }
          }
          catch (Exception ex)
          {
            it2.close();
          }
        }
      }
      
//      linkClassifiersToOptions();
    }
    
    exportAndEnrollTEIs();
  }
  
  protected void validateExport()
  {
    // Check #1 : We have to have rows in our dataset
    QueryFactory qf = new QueryFactory();
    BusinessQuery bq = qf.businessQuery(mdbiz.definesType());
    teiCount = bq.getCount();
    
    if (teiCount == 0)
    {
      DHIS2EmptyDatasetException ex = new DHIS2EmptyDatasetException();
      throw ex;
    }
    
    
    // Check #2 : Our dataset has to have a geo field
    boolean foundAGeo = false;
    OIterator<? extends MdAttribute> mdAttrs = mdbiz.getAllAttribute();
    for (MdAttribute mdAttr : mdAttrs)
    {
      if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
          !ArrayUtils.contains(MdBusinessExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
        )
      {
        if (mdAttr instanceof MdAttributeReference)
        {
          MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
          
          if (reference.definesType().equals(GeoEntity.CLASS))
          {
            foundAGeo = true;
            break;
          }
        }
      }
    }
    
    if (!foundAGeo)
    {
      GeoFieldRequiredException ex = new GeoFieldRequiredException();
      throw ex;
    }
  }
  
  protected void exportAndEnrollTEIs()
  {
    
    
    String programId = converter.getProgramId();
    
    List<? extends MdAttribute> mdAttrs = mdbiz.getAllAttribute().getAll();
    
    QueryFactory qf = new QueryFactory();
    BusinessQuery bq = qf.businessQuery(mdbiz.definesType());
    
    int page = 0;
    
    while ( (page * pageSize) < teiCount )
    {
      JSONObject teiPayload = new JSONObject();
//      JSONObject eventPayload = new JSONObject();
      
      JSONArray trackedEntityInstances = new JSONArray();
//      JSONArray events = new JSONArray();
      
      OIterator<Business> it = bq.getIterator(pageSize, page+1);
      try
      {
        while (it.hasNext())
        {
          Business biz = it.next();
          
          JSONObject trackedEntityInstance = new JSONObject();
          JSONObject event = new JSONObject();
          
          trackedEntityInstance.put("trackedEntity", trackedEntityId);
          
          GeoEntity orgUnit = null;
          
          JSONArray jAttributes = new JSONArray();
          for (MdAttribute mdAttr : mdAttrs)
          {
            if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
                !ArrayUtils.contains(MdBusinessExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
              )
            {
              JSONObject jAttribute = new JSONObject();
              String attrName = mdAttr.getAttributeName();
              jAttribute.put("attribute", trackedEntityAttributeIds.get(mdAttr.getOid()));
              
              if (mdAttr instanceof MdAttributeReference)
              {
                String refId = biz.getValue(attrName);
                if (!refId.equals(""))
                {
                  MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
                  
                  if (reference.definesType().equals(GeoEntity.CLASS))
                  {
                    orgUnit = GeoEntity.get(refId);
                  }
                  else if (reference.definesType().equals(Classifier.CLASS))
                  {
                    Classifier classy = Classifier.get(refId);
                    
                     // in 2.25 options were set via their display label here. In 2.27 they specify them via the 'code', which we have a basic oid mapping for.
                    String code = DHIS2Util.getOptionCode(classy.getOid());
                    jAttribute.put("value", code);
                  }
                  else if (reference.definesType().equals(ClassifierSynonym.CLASS))
                  {
//                    System.out.println("TODO : We just hit a ClassifierSynonym reference in tei.");
                  }
                }
              }
              else if (trackedEntityAttributeIds.containsKey(mdAttr.getOid()))
              {
                jAttribute.put("value", biz.getValue(attrName));
              }
              
              jAttribute.put("valueType", MdBusinessToTrackerJson.getDHIS2TypeFromMdAttribute(mdAttr));
              
              if (jAttribute.has("value"))
              {
                jAttributes.put(jAttribute);
              }
            }
          }
          
          if (orgUnit != null)
          {
            // TEIs //
//            String teiId = this.idCache.next();
//            trackedEntityInstance.put("oid", teiId);
            
            trackedEntityInstance.put("attributes", jAttributes);
            
            JSONArray enrollments = new JSONArray();
            JSONObject enrollment = new JSONObject();
            enrollment.put("orgUnit", orgUnit.getGeoId());
            enrollment.put("program", programId);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            enrollment.put("incidentDate", date);
            enrollment.put("enrollmentDate", date);
            enrollments.put(enrollment);
            trackedEntityInstance.put("enrollments", enrollments);
            
            // TODO : The geoId contains the DHIS2 oid, and not the actual geoid. We do this because it has to be referenced when we export and there's no other place to save the DHIS2 oid.
            // If we make this a real product perhaps we need to store a map from runway oid to dhis2 oid in some table somewhere.
            trackedEntityInstance.put("orgUnit", orgUnit.getGeoId());
            
            trackedEntityInstances.put(trackedEntityInstance);
            
            
            // Data Elements //
            
            
            
            // Events //
//            event.put("program", programId);
//            event.put("programStage", value);
//            event.put("trackedEntityInstance", teiId);
//            event.put("orgUnit", orgUnit.getGeoId());
//            event.put("eventDate", date);
          }
        }
      }
      finally
      {
        it.close();
      }
      
      teiPayload.put("trackedEntityInstances", trackedEntityInstances);
      HTTPResponse response = dhis2.httpPost("api/25/trackedEntityInstances", teiPayload.toString());
      DHIS2TrackerResponseProcessor.validateImportSummaryResponse(response);
      
//      eventPayload.put("events", events);
//      HTTPResponse response3 = dhis2.httpPost("api/25/events", eventPayload.toString());
//      DHIS2TrackerResponseProcessor.validateImportSummaryResponse(response3);
      
      page = page + 1;
    }
  }
  
  protected void createTrackedEntity()
  {
    JSONObject jsonMetadata = new JSONObject();
    
    JSONArray trackedEntities = new JSONArray();
    trackedEntities.put(converter.getTrackedEntityJson());
    jsonMetadata.put("trackedEntities", trackedEntities);
    
    HTTPResponse response = dhis2.httpPost("api/25/metadata", jsonMetadata.toString());
    DHIS2TrackerResponseProcessor.validateTypeReportResponse(response, false);
    
    this.trackedEntityId = converter.getTrackedEntityId();
  }
  
  @Transaction
  protected void createTrackedEntityAttributes()
  {
    converter.setTrackedEntityAttributeIds(trackedEntityAttributeIds);
    JSONObject metadata = converter.getTrackedEntityAttributesJSON();
    
    HTTPResponse response = dhis2.httpPost("api/25/metadata", metadata.toString());
    DHIS2TrackerResponseProcessor.validateTypeReportResponse(response, true);
    
    this.trackedEntityAttributeIds = converter.getTrackedEntityAttributeIds();
  }
  
  /**
   * Call this method for an update or 'createTrackedEntityAttributes' for new.
   */
//  protected void linkClassifiersToOptions()
//  {
//    ArrayList<Classifier> rootsToLink = new ArrayList<Classifier>();
//    
//    OIterator<? extends MdAttribute> mdAttrs = mdbiz.getAllAttribute();
//    for (MdAttribute mdAttr : mdAttrs)
//    {
//      if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
//          !ArrayUtils.contains(MdBusinessExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
//        )
//      {
//        if (mdAttr instanceof MdAttributeReference)
//        {
//          MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
//          
//          if (reference.definesType().equals(Classifier.CLASS))
//          {
//            Classifier root = Classifier.findClassifierRoot(MdAttributeTermDAO.get(mdAttr.getOid()));
//            
//            String pack = root.getClassifierPackage();
//            
//            if (!pack.startsWith(OptionSetJsonToClassifier.DHIS2_CLASSIFIER_PACKAGE_PREFIX))
//            {
//              // If its not prefixed then its not linked to anything in DHIS2
//              rootsToLink.add(root);
//            }
//          }
//        }
//      }
//    }
//    
//    for (Classifier root : rootsToLink)
//    {
//      OIterator<? extends Classifier> cit = root.getAllIsAChild();
//      while (cit.hasNext())
//      {
//        Classifier child = cit.next();
//        
//        
//      }
//    }
//  }
  
  /**
   * Fetches the oid of a category combo with the provided name
   * 
   * Example: http://localhost:8085/api/25/metadata?assumeTrue=false&categoryCombos=true
   */
  public String getCategoryComboId(String name)
  {
    String categoryComboId = null;
    
    HTTPResponse response = dhis2.httpGet("api/25/metadata", new NameValuePair[] {
      new NameValuePair("assumeTrue", "false"),
      new NameValuePair("categoryCombos", "true")
    });
    DHIS2TrackerResponseProcessor.validateStatusCode(response); // TODO : We need better validation than just status code.
    
    JSONObject json = response.getJSON();
    
    if (json != null && json.has("categoryCombos"))
    {
      JSONArray combos = json.getJSONArray("categoryCombos");
      
      for (int i = 0; i < combos.length(); ++i)
      {
        JSONObject combo = combos.getJSONObject(i);
        
        if (combo.getString("name").equals("default"))
        {
          categoryComboId = combo.getString("oid");
          break;
        }
      }
    }
    
    if (categoryComboId == null)
    {
      throw new RuntimeException("Unable to find a DHIS2 category combo by name [" + name + "].");
    }
    
    return categoryComboId;
  }
  
  protected void createProgram()
  {
    JSONObject jsonMetadata = new JSONObject();
    
    JSONArray programs = new JSONArray();
    programs.put(converter.getProgramJson(getCategoryComboId("default"), trackedEntityAttributeIds));
    jsonMetadata.put("programs", programs);
    
    HTTPResponse response = dhis2.httpPost("api/25/metadata", jsonMetadata.toString());
    DHIS2TrackerResponseProcessor.validateTypeReportResponse(response, false);
  }
  
  
  /**
   * I wrote this code back when I was creating the DHIS2 objects and then fetching their ids. Now we just generate it and tell DHIS2 what the oid is.
   * I'm keeping it here (for now) because it might be useful later (as a starting point) in case we need to fetch any of these objects for any reason.
   * This code was originally written on 2.25 but I think it also works on 2.27.
   */
//protected void getTrackedEntityAttributeIds()
//{
//trackedEntityAttributeIds = new HashMap<String, String>();
//
//List<? extends MdAttribute> mdAttrs = mdbiz.getAllAttribute().getAll();
//
//JSONObject response = dhis2.httpGet("api/25/metadata", new NameValuePair[] {
//  new NameValuePair("assumeTrue", "false"),
//  new NameValuePair("trackedEntityAttributes", "true")
//});
//
//boolean fatalResponse = false;
//
//if (response != null && response.has("trackedEntityAttributes"))
//{
//  JSONArray TEAs = response.getJSONArray("trackedEntityAttributes");
//  
//  if (TEAs.length() == 0)
//  {
//    fatalResponse = true;
//  }
//  
//  for (int i = 0; i < TEAs.length(); ++i)
//  {
//    JSONObject TEA = TEAs.getJSONObject(i);
//    
//    for (MdAttribute mdAttr : mdAttrs)
//    {
//      if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
//          !ArrayUtils.contains(skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME)) &&
//          TEA.getString("name").equals(mdAttr.getDisplayLabel().getValue())
//        )
//      {
//        trackedEntityAttributeIds.put(mdAttr.getOid(), TEA.getString("oid"));
//      }
//    }
//  }
//}
//else
//{
//  fatalResponse = true;
//}
//
//if (fatalResponse || trackedEntityAttributeIds.values().size() == 0)
//{
//  throw new RuntimeException("Unable to find previously created TrackedEntitiyAttributes.");
//}
//}
//  
//  protected void getTrackedEntityId()
//  {
//    JSONObject response = dhis2.httpGet("api/25/metadata", new NameValuePair[] {
//      new NameValuePair("assumeTrue", "false"),
//      new NameValuePair("trackedEntities", "true")
//    });
//    
//    if (response != null && response.has("trackedEntities"))
//    {
//      JSONArray trackedEntities = response.getJSONArray("trackedEntities");
//      
//      for (int i = 0; i < trackedEntities.length(); ++i)
//      {
//        JSONObject trackedEntity = trackedEntities.getJSONObject(i);
//        
//        if (trackedEntity.getString("name").equals(mdbiz.getDisplayLabel().getValue()))
//        {
//          trackedEntityId = trackedEntity.getString("oid");
//          break;
//        }
//      }
//    }
//    
//    if (trackedEntityId == null)
//    {
//      throw new RuntimeException("Unable to find previously created TrackedEntitiy.");
//    }
//  }
//  
//  protected void getProgramId()
//  {
//    JSONObject response = dhis2.httpGet("api/25/metadata", new NameValuePair[] {
//      new NameValuePair("assumeTrue", "false"),
//      new NameValuePair("programs", "true")
//    });
//    
//    if (response != null && response.has("programs"))
//    {
//      JSONArray programs = response.getJSONArray("programs");
//      
//      for (int i = 0; i < programs.length(); ++i)
//      {
//        JSONObject program = programs.getJSONObject(i);
//        
//        if (program.getString("name").equals(mdbiz.getDisplayLabel().getValue() + " Program") && program.getJSONObject("trackedEntity").getString("oid").equals(trackedEntityId))
//        {
//          programId = program.getString("oid");
//          break;
//        }
//      }
//    }
//    
//    if (programId == null)
//    {
//      throw new RuntimeException("Unable to find previously created Program.");
//    }
//  }
}
