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
package net.geoprism.data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.geoprism.ExcludeConfiguration;
import net.geoprism.ListSerializable;
import net.geoprism.gis.geoserver.GeoserverProperties;
import net.geoprism.ontology.GeoEntityUtilDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.ValueObjectDTO;
import com.runwaysdk.business.ValueQueryDTO;
import com.runwaysdk.business.ontology.TermDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.Controller;
import com.runwaysdk.mvc.Endpoint;
import com.runwaysdk.mvc.ErrorSerialization;
import com.runwaysdk.mvc.ParseType;
import com.runwaysdk.mvc.RequestParamter;
import com.runwaysdk.mvc.ResponseIF;
import com.runwaysdk.mvc.RestBodyResponse;
import com.runwaysdk.mvc.RestResponse;
import com.runwaysdk.system.gis.geo.GeoEntityDTO;
import com.runwaysdk.system.gis.geo.GeoEntityViewDTO;
import com.runwaysdk.system.gis.geo.LocatedInDTO;
import com.runwaysdk.system.gis.geo.UniversalDTO;
import com.runwaysdk.util.IDGenerator;

@Controller(url = "location")
public class LocationController implements Reloadable
{
  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF select(ClientRequestIF request, @RequestParamter(name = "id") String id, @RequestParamter(name = "universalId") String universalId, @RequestParamter(name = "existingLayers") String existingLayers) throws JSONException
  {
    GeoEntityDTO entity = GeoEntityUtilDTO.getEntity(request, id);

    return this.getLocationInformation(request, entity, universalId, existingLayers);
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF open(ClientRequestIF request, @RequestParamter(name = "id") String id, @RequestParamter(name = "existingLayers") String existingLayers) throws JSONException
  {
    GeoEntityDTO entity = GeoEntityUtilDTO.getEntity(request, id);
    List<TermDTO> ancestors = new LinkedList<TermDTO>(Arrays.asList(entity.getAllAncestors(new String[] { LocatedInDTO.CLASS })));

    /*
     * Remove the ROOT entry and add back the entity
     */
    ancestors.remove(0);
    ancestors.add(entity);

    RestResponse response = this.getLocationInformation(request, entity, null, existingLayers);
    response.set("ancestors", new ListSerializable(ancestors), new GeoEntityJsonConfiguration());

    return response;
  }

  private RestResponse getLocationInformation(ClientRequestIF request, GeoEntityDTO entity, String universalId, String existingLayers) throws JSONException
  {
    List<? extends UniversalDTO> universals = entity.getUniversal().getAllContains();

    String layers = GeoEntityUtilDTO.publishLayers(request, entity.getId(), universalId, existingLayers);

    ValueQueryDTO children = GeoEntityUtilDTO.getChildren(request, entity.getId(), universalId, 200);

    RestResponse response = new RestResponse();
    response.set("children", children);
    response.set("layers", new JSONArray(layers));
    response.set("universals", new ListSerializable(universals));
    response.set("entity", new GeoEntitySerializable(entity), new GeoEntityJsonConfiguration());
    response.set("universal", ( universalId != null && universalId.length() > 0 ) ? universalId : "");
    response.set("workspace", GeoserverProperties.getWorkspace());

    return response;
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF suggestions(ClientRequestIF request, @RequestParamter(name = "text") String text, @RequestParamter(name = "limit") Integer limit) throws JSONException
  {
    ValueQueryDTO results = GeoEntityUtilDTO.getGeoEntitySuggestions(request, null, null, text, limit);

    return new RestBodyResponse(results);
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF apply(ClientRequestIF request, @RequestParamter(name = "entity", parser = ParseType.BASIC_JSON) GeoEntityDTO entity, @RequestParamter(name = "parentId") String parentId) throws JSONException
  {
    if (entity.getGeoId() == null || entity.getGeoId().length() == 0)
    {
      entity.setGeoId(IDGenerator.nextID());
    }

    if (entity.isNewInstance())
    {
      GeoEntityViewDTO dto = GeoEntityDTO.create(request, entity, parentId, LocatedInDTO.CLASS);

      JSONObject object = new JSONObject();
      object.put(GeoEntityDTO.TYPE, ValueObjectDTO.CLASS);
      object.put(GeoEntityDTO.ID, dto.getGeoEntityId());
      object.put(GeoEntityDTO.DISPLAYLABEL, dto.getGeoEntityDisplayLabel());
      object.put(GeoEntityDTO.GEOID, entity.getGeoId());
      object.put(GeoEntityDTO.UNIVERSAL, dto.getUniversalDisplayLabel());

      return new RestBodyResponse(object);
    }
    else
    {
      String id = entity.getId();
      
      entity.apply();

      JSONObject object = new JSONObject();
      object.put(GeoEntityDTO.TYPE, ValueObjectDTO.CLASS);
      object.put(GeoEntityDTO.ID, entity.getId());
      object.put(GeoEntityDTO.DISPLAYLABEL, entity.getDisplayLabel().getValue());
      object.put(GeoEntityDTO.GEOID, entity.getGeoId());
      object.put(GeoEntityDTO.UNIVERSAL, entity.getUniversal().getDisplayLabel().getValue());
      object.put("oid", id);

      return new RestBodyResponse(object);
    }
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF edit(ClientRequestIF request, @RequestParamter(name = "entityId") String entityId) throws JSONException
  {
    GeoEntityDTO entity = GeoEntityDTO.lock(request, entityId);

    return new RestBodyResponse(entity, new ExcludeConfiguration(GeoEntityDTO.class, GeoEntityDTO.WKT));
  }
  
  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF unlock(ClientRequestIF request, @RequestParamter(name = "entityId") String entityId) throws JSONException
  {
    GeoEntityDTO.unlock(request, entityId);
    
    return new RestBodyResponse("");
  }
}