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

  function BuilderDialogController($scope, $rootScope, $timeout, builderService, dataService) {
    var controller = this;
    
    controller.clear = function() {
      $scope.show = false;
      $scope.busy = false;   
      $scope.showWidgetType = 'DESCRIPTION';
      $scope.errors = [];
        
      $scope.fields = null;
      $scope.dashboard = null;    
    }
    
    controller.cancel = function() {
      var onSuccess = function(newInstance) {      
        controller.clear();
        
        if(!newInstance) {
          $scope.$apply();
        }
      }
        
      builderService.unlock($scope.dashboard, onSuccess);
    }
    
    controller.persist = function() {
      $scope.busy = true;
   
      var onSuccess = function(result) {      
        var layerNames = JSON.parse(result);
        
        if(layerNames.length > 0) {
          var message = com.runwaysdk.Localize.localize("dashboardViewer", "removeLayers", "This change will remove the following layers: {0}");
          message = message.replace('{0}', layerNames.toString());
                
          var dialog = com.runwaysdk.ui.Manager.getFactory().newDialog(com.runwaysdk.Localize.localize("dashboardViewer", "information", "Information"));
          dialog.appendContent(message);          
          dialog.addButton(com.runwaysdk.Localize.localize("dashboardViewer", "ok", "Ok"), function() {
            $scope.busy = false;            
            dialog.close();            
            $scope.$apply();
            
            controller.applyWithOptions(); 
          }, null, {class:'btn btn-primary'});
          dialog.addButton(com.runwaysdk.Localize.localize("dashboardViewer", "cancel", "Cancel"), function() {
        $scope.busy = false;            
            dialog.close();            
            $scope.$apply();
          }, null, {class:'btn btn-default'});
          dialog.setStyle("z-index", 10001);          
          dialog.render();
        }
        else {
          controller.applyWithOptions();
        }
      }    
      
      builderService.getLayersToDelete($scope.dashboard,'#ng-modal-overlay', onSuccess);
    }
    
    controller.applyWithOptions = function() {
      var onSuccess = function(result) {      
        controller.clear();
            
        $scope.$apply();

        var dashboard = JSON.parse(result);

        $scope.$emit('dashboardChange', {dashboard:dashboard});            
      }
          
      var onFailure = function(e){
    $scope.errors = [];
    $scope.errors.push(e.message);
                       
        $scope.$apply();
              
        $('#builder-div').parent().parent().animate({ scrollTop: 0 }, 'slow');
      };             
                     
      // Clear all the errors
      $scope.errors = [];
          
      builderService.applyWithOptions($scope.dashboard,'#ng-modal-overlay', onSuccess, onFailure);
    }
    
    controller.init = function(result) {
      controller.clear();
      
      $scope.fields = result.fields;    
      $scope.dashboard = result.object;
      $scope.show = true;
    }
    
    controller.load = function(dashboardId) {
        
      var onSuccess = function(result) {
        
      controller.init(result);
      
        $scope.$apply();
      }
               
      builderService.loadDashboard(dashboardId, onSuccess);    
    }
    
    controller.setCategoryWidgetType = function(typeName) {
      $scope.showWidgetType = typeName;
    }
    
    /*
     * Data Upload Section
     */
    controller.uploadSpreadsheet = function() {
      var onSuccess = function(response) {
        var sheets = JSON.parse(response);
    
        $scope.$emit('dataUpload', {sheets:sheets});            
      }
      
      var onFailure = function(e){
      $scope.errors = [];
      $scope.errors.push(e.message);
                         
        $scope.$apply();
                
        $('#builder-div').parent().parent().animate({ scrollTop: 0 }, 'slow');
      };             
        
      dataService.uploadSpreadsheet($scope.file, onSuccess, onFailure);
    }
    
    controller.setSpreadsheet = function(file) {
      $scope.file = file;
      $scope.errors = [];
    }
    
    /*
     * Event Listeners
     */
    $rootScope.$on('editDashboard', function(event, data){
      controller.load(data.dashboardId);
    });
    
    $rootScope.$on('newDashboard', function(event, data){
      controller.load(null);
    });
    
    // Initialize an empty controller
    controller.clear();
  }
  
  function BuilderDialog() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/builder/builder-dialog.jsp',
      scope: {
      },
      controller : BuilderDialogController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }
  
  function TextField($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/builder/text-field.jsp',
      require: '^form',      
      scope: {
        field:'=',
        model:'=',
        maxlength:'=',
        minlength:'=',
        placeholdertext:'='
      },
      link: function (scope, element, attrs, form) {
        scope.form = form;  
        
        $timeout(function() {
          if(attrs.maxlength && attrs.maxlength > 0){
              scope.field.maxlength = attrs.maxlength;
            }
            else{
              scope.field.maxlength = 5000; // arbitrary default
            }
            
            if(attrs.minlength && attrs.minlength > 0){
              scope.field.minlength = attrs.minlength;
            }
            else{
              scope.field.minlength = 0; // arbitrary default
            }
            
            if(attrs.placeholdertext && attrs.placeholdertext.length > 0){
              if(attrs.placeholdertext.indexOf("'") == 0){
                scope.field.placeholdertext = attrs.placeholdertext.replace("'", "").slice(0, -1);
              }
              else{
                scope.field.placeholdertext = attrs.placeholdertext;
              }
            }
            else{
              scope.field.placeholdertext = ""; // arbitrary default
            }
          }, 500);
      }
    }    
  }
  
  function TextAreaField($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/builder/text-area-field.jsp',
      require: '^form',      
      scope: {
        field:'=',
        model:'=',
        maxlength:'=',
        minlength:'=',
        placeholdertext:'='
      },
      link: function (scope, element, attrs, form) {
        scope.form = form;  
        
        $timeout(function() {
          if(attrs.maxlength && attrs.maxlength > 0){
              scope.field.maxlength = attrs.maxlength;
            }
            else{
              scope.field.maxlength = 5000; // arbitrary default
            }
            
            if(attrs.minlength && attrs.minlength > 0){
              scope.field.minlength = attrs.minlength;
            }
            else{
              scope.field.minlength = 0; // arbitrary default
            }
            
            if(attrs.placeholdertext && attrs.placeholdertext.length > 0){
              if(attrs.placeholdertext.indexOf("'") == 0){
                scope.field.placeholdertext = attrs.placeholdertext.replace("'", "").slice(0, -1);
              }
              else{
                scope.field.placeholdertext = attrs.placeholdertext;
              }
            }
            else{
              scope.field.placeholdertext = ""; // arbitrary default
            }
          }, 500);
      }
    }    
  }
  
  function SelectFieldController($scope, $timeout) {
    var controller = this;

    controller.init = function(element) {
    }    
  }
  
  function SelectField() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/builder/select-field.jsp',
      scope: {
        field:'=',
        model:'='
      },
      require: ['^form', 'selectField'],            
      controller : SelectFieldController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrls) {
        scope.form = ctrls[0];
    
        element.ready(function(){
          ctrls[1].init(element);        
        });
      }
    }    
  }
  
  function TypeAttributeController($scope) {
    $scope.$watch('attribute.selected', function() {
      if($scope.attribute.selected && !$scope.type.value){
        $scope.type.value = true;          
      }
    }, true);
        
    $scope.$watch('type.value', function() {
      if(!$scope.type.value && $scope.attribute.selected){
        $scope.attribute.selected = false;          
      }
    }, true);              
  }
  
  function TypeAttribute() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/builder/type-attribute.jsp',
      scope: {
        attribute:'=',
        type:'='
      },
      controller : TypeAttributeController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }
  
  function ModalDialog() {
    return {
      restrict: 'E',
      scope: {
      },
      replace: true, // Replace with the template below
      transclude: true, // we want to insert custom content inside the directive
      templateUrl: '/partial/dialog/modal-dialog.jsp',
      link: function(scope, element, attrs) {
        scope.dialogStyle = {};
        
        if (attrs.width) {
          scope.dialogStyle.width = attrs.width;        
        }
        
        if (attrs.height) {
          scope.dialogStyle.height = attrs.height;        
        }
        
        scope.hideModal = function() {
          scope.show = false;
        };
      }
    };
  }


  angular.module("dashboard-builder", ["builder-service", "data-service", "styled-inputs"]);
  angular.module("dashboard-builder")
   .directive('textField', TextField)
   .directive('textAreaField', TextAreaField)
   .directive('selectField', SelectField)
   .directive('modalDialog', ModalDialog)
   .directive('typeAttribute', TypeAttribute)
   .directive('builderDialog', BuilderDialog);
})();