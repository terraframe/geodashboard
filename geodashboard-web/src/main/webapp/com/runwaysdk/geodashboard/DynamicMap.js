(function(){
	
  var Component = com.runwaysdk.ui.Component;  
  var DynamicMapName = GDB.Constants.GIS_PACKAGE+'DynamicMap';
  
  /**
  * LANGUAGE
  */
  com.runwaysdk.Localize.defineLanguage(DynamicMapName, {
	 "googleStreets" : "Google Streets",
	 "googleSatellite" : "Google Satellite",
	 "googleHybrid" : "Google Hybrid",
	 "googleTerrain" : "Google Terrain",
	 "osmBasic" : "Open Street Map"
  });
  
  var DynamicMap = Mojo.Meta.newClass(GDB.Constants.GIS_PACKAGE+'DynamicMap', {
	  
	Extends : Component,
    
    Constants : {
      BASE_LAYER_CONTAINER : 'baseLayerContainer',
      OVERLAY_LAYER_CONTAINER : 'overlayLayerContainer',
      GEOSERVER_WORKSPACE : 'geodashboard',
      GEOCODE : 'geocode',
      GEOCODE_LABEL : 'geocodeLabel',
      LAYER_MODAL : '#modal01'
    },
    
    Instance : {
      
      /**
       * Constructor
       * @param mapDivId DOM id of the map div.
       */
      initialize : function(mapDivId, mapId){
        this.$initialize();
        
        this._mapDivId = mapDivId;
        this._mapId = mapId;
        this._mdAttribute = null;
        this._map = new L.Map(this._mapDivId,{zoomAnimation: false,zoomControl: true});
        
        this._defaultOverlay = null;
        this._currentOverlay = null;
        this._overlayLayers = new com.runwaysdk.structure.HashMap();     
        
        // The current base map (only one at a time is allowed)
        this._defaultBase = null;
        this._currentBase = null;
        this._baseLayers = new com.runwaysdk.structure.HashMap();
        
        this._suggestionCoords = new com.runwaysdk.structure.HashMap();      
        this._autocomplete = null;
        this._responseCallback = null;
        
        this._rendered = false;
        
        this._LayerController = com.runwaysdk.geodashboard.gis.persist.DashboardLayerController;
        
        // set controller listeners
        this._LayerController.setCancelListener(Mojo.Util.bind(this, this._cancelListener));
        this._LayerController.setApplyWithStyleListener(Mojo.Util.bind(this, this._applyWithStyleListener));
      },
      
      /**
       * Closes the overlay with the layer/style CRUD.
       * 
       */
      _closeLayerModal : function(){
        $(DynamicMap.LAYER_MODAL).modal('hide').html('');
      },
      
      /**
       * Called when a user submits (creates/updates) a layer with styles.
       * 
       * @param params
       */
      _applyWithStyleListener : function(params){
        
        var that = this;
        
        var request = new GDB.StandardRequest({
          onSuccess : function(json){
            console.log('done');
            var obj = Mojo.Util.toObject(json);
            console.log(obj);
            
            that._closeLayerModal();
            that._refreshMap();
          }
        });
        
        params['mapId'] = this._mapId;
        params['style.mdAttribute'] = this._mdAttribute;
        
        // Custom conversion to turn the checkboxes into boolean true/false
        params['style.enableLabel'] = params['style.enableLabel'].length > 0;
        params['style.enableValue'] = params['style.enableValue'].length > 0;
        params['layer.displayInLegend'] = params['layer.displayInLegend'].length > 0;
        
        return request;
      },
      

      
      /**
       * 
       * @param params
       */
      _cancelListener : function(params){
        
        var that = this;
        
        if(params['layer.isNew'] === 'true')
        {
          this._closeLayerModal();
        }
        else
        {
          var that = this;
          var request = new GDB.StandardRequest({
            onSuccess : function(params){
              that._closeLayerModal();
            }
          });
          
          return request;
        }
      },
      
      /**
       * Return all allowable base maps.
       */
      getBaseLayers : function(){
        
        // the SATELLITE layer has all 22 zoom level, so we add it first to
        // become the internal base layer that determines the zoom levels of the
        // map.
        var gsat = new L.Google('SATELLITE');        	
        var gphy = new L.Google('TERRAIN');       
        var gmap = new L.Google('ROADMAP');       
        var ghyb = new L.Google('HYBRID');
        
        var osm = new L.TileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'); 
        osm._gdbcustomtype = 'OSM';
        
        var base = [gmap, gsat, ghyb, gphy, osm];
        
        return base;
      },
      
      /**
       * Renders each base layer as a checkable option in
       * the layer switcher.
       */
      _renderBaseLayerSwitcher : function(base){
        
        var container = $('#'+DynamicMap.BASE_LAYER_CONTAINER);
        var el = container[0];
        
        // Create the HTML for each row (base layer representation).
        var html = '';
        var ids = [];
        for(var i=0; i<base.length; i++){
          
          var id = 'base_layer_'+i;
          
          var b = base[i];
          b.id = id;
          
          var checked = '';
          if(i === 0){
            this._currentBase = this._defaultBase = base[0];
            checked = 'checked="checked"';
          }
          
          ids.push(id);
          
          // Assigning better display labels.
          var label = '';
          if(b._type === 'ROADMAP'){
        	  label = this.localize("googleStreets");
          }
          else if(b._type === 'SATELLITE'){
        	  label = this.localize("googleSatellite");
          }
          else if(b._type === 'TERRAIN'){
        	  label = this.localize("googleTerrain");
          }
          else if(b._type === 'HYBRID'){
        	  label = this.localize("googleHybrid");
          }
          else if(b._gdbcustomtype === 'OSM'){
        	  label = this.localize("osmBasic");
          }

          html += '<div class="row-form">';
          html += '<input id="'+id+'" class="check" type="checkbox" '+checked+'>';
          html += '<label for="'+id+'">'+ label +'</label>';
          html += '</div>';
          
          this._baseLayers.put(id, b);
        }

        // combine the rows into new HTML that goes in to the layer switcher
        var rows = $(html);

        var container = $('#'+DynamicMap.BASE_LAYER_CONTAINER);
        var el = container[0];    
        
        container.append(rows);
        
        jcf.customForms.replaceAll(el);
        
        // add event handlers to manage the actual check/uncheck process
        for(var i=0; i<ids.length; i++){
          var id = ids[i];
          var check = $('#'+id);
          
          var handler = Mojo.Util.bind(this, this._selectBaseLayer);
          check.on('change', this._baseLayers.get(id), handler);
        }
        
      },
      
      /**
       * Changes the base layer of the map.
       * 
       * @param e
       */
      _selectBaseLayer : function(e){
    	  				
        var changed = e.currentTarget;
        var changedId = changed.id;
        var changedLayer = this._baseLayers.get(changedId);
        
        var ids = this._baseLayers.keySet();
        
        var newBaseLayer = null;
        if(changed.checked){
          // uncheck other base layers without firing the event (to avoid infinte event looping)
          for(var i=0; i<ids.length; i++){
            
            var id = ids[i];
            if(id === changedId){
              newBaseLayer = changedLayer;
              document.getElementById(id).disabled=true;
            }
            else{
              document.getElementById(id).disabled=false;
              var uncheck = document.getElementById(id);
              uncheck.checked = false;
              jcf.customForms.refreshElement(uncheck);
              
              var layer = this._baseLayers.get(id);
              this._map.removeLayer(layer);             	
            }
          }
        }
        // then add the layer and set it to stack below the overlays
        this._map.addLayer(newBaseLayer);
        if(newBaseLayer._gdbcustomtype === 'OSM'){
        	newBaseLayer.bringToBack();  // this throws an error for non OSM layers 
        }
      },
      
      /**
       * Toggles the overlay layers of the map.
       * 
       * @param e
       */
      _selectOverlayLayer : function(e){
    	  
          var changed = e.currentTarget;
          var changedId = changed.id;
          var changedLayer = this._overlayLayers.get(changedId);
          var ids = this._overlayLayers.keySet();
          
          var newOverlayLayer = null;
          if(changed.checked){
        	  for(var i=0; i<ids.length; i++){
        		  var id = ids[i];
        		  if(id === changedId){
        			  newOverlayLayer = changedLayer;
        			  this._map.addLayer(newOverlayLayer);
        		  }
        	  }
          }
          else{
        	  for(var i=0; i<ids.length; i++){            	  
                  var id = ids[i];
                  if(id === changedId){
                	  var uncheck = document.getElementById(id);
                	  uncheck.checked = false;
                	  jcf.customForms.refreshElement(uncheck);
                  
                	  var removeLayer = this._overlayLayers.get(id);
                	  this._map.removeLayer(removeLayer);
                  }
        	  }
          }
      },
      
      /**
       * Builds the autocomplete and renders the map using Leaflet.
       */
      render : function(){
        
        var that = this;
        
        this._autocomplete = $('#'+DynamicMap.GEOCODE).autocomplete({
          minLength: 2,
          select : function(value, data){          
            var loc = data.item.value;
            var lonlat = that._suggestionCoords.get(loc);

            that._map.setView(new L.LatLng(lonlat[1], lonlat[0]), 13);
            
            that._suggestionCoords.clear();
          },
          source: function(request, response){
          
            that._suggestionCoords.clear();
            
            var term = request.term;
            
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode({ 'address': term }, function (results, status) {
              
              if (status == google.maps.GeocoderStatus.OK) {              
                var rows = Mojo.Util.isArray(results) ? results : [results];
                
                var suggestions = [];
                for(var i=0; i<rows.length; i++){                 
                  var row = rows[i];
                  
                  suggestions.push(row.formatted_address);
                  
                  var lon = row.geometry.location.A;
                  var lat = row.geometry.location.k;
                  that._suggestionCoords.put(row.formatted_address, [lon, lat]);
                }
                response(suggestions);
              }
            });      
          },
        });
//        that._autocomplete.show();      
//        $('#'+DynamicMap.GEOCODE).on('keypress', Mojo.Util.bind(this, this._geocodeHandler));

         this._refreshMap();
        
         // Make sure all openers for each attribute have a click event
         $('a.attributeLayer').on('click', Mojo.Util.bind(this, this._openLayerForAttribute));
      },
      
      _refreshMapInternal : function(jsonObj){
        
        // FIXME Lewis
        if(this._rendered){
          $('#'+DynamicMap.BASE_LAYER_CONTAINER).html('');
          $('#'+DynamicMap.OVERLAY_LAYER_CONTAINER).html('');
          this._baseLayers.clear();
          this._map.remove();
          $('#'+this._mapDivId).html('');
          this._map = new L.Map(this._mapDivId,{zoomAnimation: false,zoomControl: true});
        }
        
        var jsonBbox = jsonObj.bbox; 
        var jsonLayers = jsonObj.layers;

        var swLatLng = L.latLng(jsonBbox[1], jsonBbox[0]);
        var neLatLng = L.latLng(jsonBbox[3], jsonBbox[2]);            
        var bounds = L.latLngBounds(swLatLng, neLatLng);   

        this._map.fitBounds(bounds);

        // Add attribution to the map
        this._map.attributionControl.setPrefix('');
        this._map.attributionControl.addAttribution("TerraFrame | GeoDashboard");

        // Hide mouse position coordinate display when outside of map
        this._map.on('mouseover', function(e) {
          $(".leaflet-control-mouseposition.leaflet-control").show();
        });

        this._map.on('mouseout', function(e) {
          $(".leaflet-control-mouseposition.leaflet-control").hide();
        });

        L.control.mousePosition({emptyString:"",position:"bottomleft",prefix:"Lat: ",separator:" Long: "}).addTo(this._map);

        // Add Base Layers to map and layer switcher panel
        var base = this.getBaseLayers();                                
        this._map.addLayer(base[0]); 
        this._renderBaseLayerSwitcher(base);

        var container = $('#'+DynamicMap.OVERLAY_LAYER_CONTAINER);
        
        //// Add associated Overlays
        // @viewName
        // @sldName - must be a valid style registered with geoserver (no .sld extension) or the default for that layer will be used.
        // @displayName
        // @geoserverName - must be include workspace and layername (ex: workspace:layer_name).         
        var html = '';
        var ids = [];
        for(var i = 0; i < jsonLayers.length; i++){
          var viewName = jsonObj.layers[i].viewName;
          var sldName = jsonObj.layers[i].sldName || "";  // This should be enabled we wire up the interface or set up a better test process
          var displayName = jsonObj.layers[i].layerName || "N/A";
          var geoserverName = DynamicMap.GEOSERVER_WORKSPACE + ":" + viewName;

          var layer = L.tileLayer.wms(window.location.origin+"/geoserver/wms/", {
            layers: geoserverName,
            format: 'image/png',
            transparent: true,
            styles: sldName
          });             

          // Create the HTML for each row (base layer representation).
          var checked = '';
          var id = 'overlay_layer_'+i;                  
          var b = layer;
          b.id = id;  
          ids.push(id);  
          this._overlayLayers.put(id, b);

          // This if statement is completely unneeded but makes sure a single layer is rendered on the map.  
          // It often helps new users to see an overlay in action on initial map load.
          if(i === 0){
            this._currentOverlay = this._defaultOverlay = layer;
            checked = 'checked="checked"';
            this._map.addLayer(layer);
          }

          html += '<div class="row-form">';
          html += '<input id="'+id+'" class="check" type="checkbox" '+checked+'>';
          html += '<label for="'+id+'">'+displayName+'</label>';
          html += '<div class="cell"><a href="#" class="ico-remove">remove</a><a href="#" class="ico-edit">edit</a><a href="#" class="ico-control">control</a></div>';
          html += '</div>';                   
        }

        // combine the rows into new HTML that goes in to the layer switcher
        var rows = $(html);
        var el = container[0];

        container.append(rows);               
        jcf.customForms.replaceAll(el);

        // add event handlers to manage the actual check/uncheck process
        for(var i=0; i<ids.length; i++){
          var id = ids[i];
          var check = $('#'+id);                    
          var handler = Mojo.Util.bind(this, this._selectOverlayLayer);
          check.on('change', this._overlayLayers.get(id), handler);
        }
        
        this._rendered = true;       
      },
      
      _refreshMap : function(){
        
        var that = this;
        com.runwaysdk.geodashboard.gis.persist.DashboardMap.getMapJSON(
            new GDB.StandardRequest({
                onSuccess : function(json){
                  var jsonObj = Mojo.Util.toObject(json);
                  that._refreshMapInternal(jsonObj);
                }
            })
            , this._mapId);
      },
      
      _openLayerForAttribute : function(e){
        e.preventDefault();

        var el = $(e.currentTarget);
        var attrId = el.data('id');
        this._mdAttribute = attrId;
        
        var that = this;
        
        var request = new GDB.StandardRequest({
          onSuccess : function(html){
            
            var exec = Mojo.Util.extractScripts(html);
            
            var modal = $(DynamicMap.LAYER_MODAL).first();
            modal.html(html);
            jcf.customForms.replaceAll(modal[0]);
            
            eval(exec);
            
            // Add layer styling event listeners
            ////
            that._selectColor();   // test
            that._selectLayerType(); // test
          }
        });
        
        this._LayerController.newInstance(request);
        
      },
      
      /**
       * Handles the selection of colors from the color picker 
       * 
       * 
       */
      _selectColor : function(){
    	  
    	  // color dropdown buttons
    	  $('.color-holder').colpick({
    			submit:0,  // removes the "ok" button which allows verification of selection and memory for last color
    			onChange:function(hsb,hex,rgb,el,bySetColor) {
    				$(el).find(".ico").css('background','#'+hex);
    				$(el).find('.color-input').attr('value', '#'+hex);
    			}
    		}); 
    	  
    	  // category layer type colors
    	  $("#category-colors-container").find('.icon-color').colpick({
  			submit:0,  // removes the "ok" button which allows verification of selection and memory for last color
  			onChange:function(hsb,hex,rgb,el,bySetColor) {
  				$(el).css('background','#'+hex);
  				$(el).find('.color-input').attr('value', '#'+hex);
  			}
  		});
      },
      
      /**
       * Handles the selection of layer type representation  
       * i.e. basic, bubble, gradient, category
       * 
       */
      _selectLayerType : function(){
    	  var layerType = com.runwaysdk.geodashboard.gis.persist.DashboardLayer.LAYERTYPE;
    	  $('input:radio[name="layer.'+layerType+'"]').change(function(){ 	
    		  		
    		  		var targetRadio = $(this);
    		  		
    		  		// hide all the styling options
	        		$.each($('.tab-pane'), function(){
	        			if($(this).is(":visible")){
	        				$(this).hide();	
	        			}
	        		});
	        	
	        		// add the relevant styling options for the layer type
			        if (targetRadio.attr("id") === "radio1") {
			            $("#tab001").show();
			        }
			        else if (targetRadio.attr("id") === "radio2") {
			            $("#tab002").show();
			        }
			        else if (targetRadio.attr("id") === "radio3") {
			            $("#tab003").show();
			        }
			        else if (targetRadio.attr("id") === "radio4") {
			            $("#tab004").show();
			        }
    		});
      }
    }
   
  });
  
  var DataType = Mojo.Meta.newClass(GDB.Constants.GIS_PACKAGE+'DataType', {
  
    Instance : {
      initialize: function(label){
        this.$initialize();
        
        this.label = label;
      }
    }
  });
  
  var AbstractAttribute = Mojo.Meta.newClass(GDB.Constants.GIS_PACKAGE+'AbstractAttribute', {
    
    Instance : {
      initialize: function(label){
        this.$initialize();
        
        this.label = label;
      }
    }
  });
  
})();

