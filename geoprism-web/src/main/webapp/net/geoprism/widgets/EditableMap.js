/*
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
(function(){
  function EditableMapController($scope, localizationService, mapService, $attrs) {
	 var controller = this;
	  
	 $scope.enableEdits = $attrs.enableedits == "true"; // evaluate string to boolean... JavaScript is rediculous
	 $scope.overlayLayerCache = {values:{}, ids:[]};
	 $scope.bbox = [];
	 $scope.renderBase = true;
	 $scope.baseLayers = [];
	 $scope.contextStyle = {fill:"rgba(255, 255, 255, 0.0)", strokeColor:"black", strokeWidth:3};
	 $scope.targetStyle = {fill:"rgba(255, 255, 255, 0.0)", strokeColor:"red", strokeWidth:2};
	 
	 $scope.contextGeoJSON = null;
	 $scope.targetGeoJSON = null;
	 
	 controller.init = function() {
		 
		$scope.baseLayers = mapService.createBaseLayers();
		 
		// enable the default base layer 
	    $scope.baseLayers[0].isActive = true;
		controller.refreshBaseLayer();
		
		var parentLayer = controller.getParentLayerData(false, false);
		$scope.contextGeoJSON = parentLayer;
		
		var childLayer = controller.getDirectChildLayerData(true, true);
		$scope.targetGeoJSON = childLayer;
		
		controller.addVectorHoverEvents();
		controller.addVectorClickEvents();
		
		//
		// Use this to enable the edit control
		//
		if($scope.enableEdits){
			controller.enableEdits();
		}
	 }
	 
	 controller.enableEdits = function() {
		 mapService.enableEdits();
	 }
	 
	 controller.replaceVectorData = function() {
		 mapService.removeAllVectorLayers();
		 
		 $scope.contextGeoJSON = controller.getParentLayerData(false, false);
		 $scope.targetGeoJSON = controller.getDirectChildLayerPointData(true, true);
//		 controller.addVectorLayer(newData, $scope.targetStyle, 2);
	 }
	 
	 controller.getParentLayerData = function(isHoverable, isClickable) {
		 return mapService.getMockJSONMapLayersParent(isHoverable, isClickable);
	 }
	 
	 controller.getDirectChildLayerData = function(isHoverable, isClickable) {
		 return mapService.getMockJSONMapLayersPolygons(isHoverable, isClickable);
	 }
	 
	 controller.getDirectChildLayerPointData = function(isHoverable, isClickable) {
		 return mapService.getMockJSONMapLayersPoints(isHoverable, isClickable);
	 }
	 
	 controller.addVectorClickEvents = function() {
		 mapService.addVectorClickEvents();
	 }
	 
	 controller.addVectorHoverEvents = function() {
		 mapService.addVectorHoverEvents();
	 }
	 
	 controller.zoomToVectorDataExtent = function() {
		 mapService.zoomToVectorDataExtent();
	 }
	  
	 controller.loadMapState = function() {  
		controller.clearMapState();
	      
		  //
		  //
		  // Get map data and update with setMapState
		  //
		  // controller.setMapState(state, true);

	 }
	  
	 controller.clearMapState = function() {
		$scope.overlayLayerCache = {values:{}, ids:[]};
		$scope.bbox = [];
		$scope.renderBase = true;
	 }
	 
	 controller.addVectorLayer = function(layerGeoJSON, styleObj, stackingIndex) {
		 mapService.addVectorLayer(layerGeoJSON, styleObj, stackingIndex);
	 }
	  
	  
	 /* Layer Cache Getters/Setters */
	 controller.getLayerCache = function() {
	    return $scope.overlayLayerCache;
	 }
	    
	 controller.getLayer = function(layerId) {
	      //// replaced because this doesn't actually get the layer
	      //return controller.getLayerCache()[layerId];   
	      ////
	    return controller.getLayerCache().values[layerId];
	 }
	    
	 controller.getThematicLayers = function() {
	      var layers = [];
	      
	      for(var i = 0; i < $scope.overlayLayerCache.ids.length; i++) {
	        var layerId = $scope.overlayLayerCache.ids[i];
	        
	        layers.push($scope.overlayLayerCache.values[layerId]);
	      }
	      
	      return layers;
	 }
	  
	  
	 controller.refreshBaseLayer = function() {
	      if($scope.baseLayers.length > 0) {
	        for(var i = 0; i < $scope.baseLayers.length; i++) {
	          var layer = $scope.baseLayers[i];
	          
	          if(layer.isActive) {
	            mapService.showLayer(layer, 0);          
	          }
	          else {
	            mapService.hideLayer(layer);        
	          }
	        }
	      }
	  }
	  
	    
      controller.refreshMap = function() {
    	
      }
      
      
      $scope.$watch('contextGeoJSON', function(newValue, oldValue) {
    	  controller.addVectorLayer(newValue, $scope.contextStyle, 1);
    	  controller.zoomToVectorDataExtent();
      });
      
      $scope.$watch('targetGeoJSON', function(newValue, oldValue) {
    	  controller.addVectorLayer(newValue, $scope.targetStyle, 2);
    	  controller.zoomToVectorDataExtent();
      });
    
      controller.init();
  }
  
  
  function EditableMap() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/widgets/editable-map.jsp',
      scope: true,
      controller : EditableMapController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs, ctrl) {
      }
    }   
  }
  
  
  angular.module("editable-map", ["styled-inputs", "localization-service", "map-service"]);
  angular.module("editable-map")
   .controller('EditableMapController', EditableMapController)
   .directive('editableMap', EditableMap)
})();