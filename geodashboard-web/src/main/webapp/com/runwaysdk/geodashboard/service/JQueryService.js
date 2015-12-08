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
  
  function JQueryService() {
    var service = {};    
    
    service.animate = function(element, properties, duration, complete) {
      $(element).animate(properties, duration, complete);            	
    }
    
    service.sortable = function(element, update) {
      element.ready(function(){
        element.sortable({
          update: update
        });
      });                
    }
    
    return service;
  }
  
  angular.module("jquery-service", []);
  angular.module("jquery-service")
    .factory('jQueryService', JQueryService)
})();