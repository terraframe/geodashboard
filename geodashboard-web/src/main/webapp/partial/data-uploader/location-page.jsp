<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/geodashboard.tld" prefix="gdb"%>

<div>
  <ng-form name="ctrl.attributeForm" isolate-form ng-if="attribute != null">
    <div class="label-holder">
      <strong>Text Location Field Creator</strong>
    </div>    
    <div class="holder">
      <div class="location-selector-container">
          <span class="text">
	        <input ng-model="attribute.label" name="label" ng-required="true" type="text" validate-unique validator="ctrl.isUniqueLabel"></input>
	      </span>
	      <div class="error-message">
	        <p ng-show="ctrl.attributeForm.label.$error.unique">
	          <gdb:localize key="dataUploader.unique"/>
	        </p>    
	      </div>
	      <div class="row-holder" ng-repeat="universal in universalOptions">
	        <div class="box">
	          <select class="select-area" ng-model="attribute.fields[universal.value]" name="{{::$index + '-universal'}}" ng-required="true">
	            <option value=""></option>          
				<%-- <option value=""><gdb:localize key="dataUploader.select"/> {{universal.label}}</option>    --%>
	            <option ng-repeat="field in locationFields[universal.value]" value="{{field.label}}">{{field.label}}</option>   
	            <option value="EXCLUDE"><gdb:localize key="dataUploader.exclude"/></option>
	          </select>
	        </div>      
	      </div>
      	<div class="row-holder">
        	<div class="button-holder">
          		<input type="button" value="+" class="btn btn-primary set-location-btn pull-right"  ng-click="ctrl.newAttribute()" ng-disabled="ctrl.attributeForm.$invalid" />
        	</div>
      	</div>  
      </div>  
    </div>
  </ng-form>  

  <div ng-if="sheet.attributes.ids.length > 0">
    <div class="label-holder">
      <strong><gdb:localize key="dataUploader.attributes"/></strong>
    </div>
    <div class="holder">
      <div class="row-holder">
        
        <div ng-repeat="id in sheet.attributes.ids" ng-init="attribute = sheet.attributes.values[id]" class="location-field-info-card col-md-6">
            <h3 class="location-field-info-card-title">{{attribute.label}}</h3>
            <div class="cell">            
            	<a href="#" class="fa fa-pencil ico-edit" ng-click="ctrl.edit(attribute)" title="<gdb:localize key="dataUploader.editToolTip"/>"></a>
            	<a href="#" class="fa fa-trash-o ico-remove" ng-click="ctrl.remove(attribute)" title="<gdb:localize key="dataUploader.deleteToolTip"/>"></a>
          	</div>
          	
<!--           	<div ng-repeat="universal in universals" ng-if="attribute.fields[universal.value] != null && attribute.fields[universal.value] != 'EXCLUDE'"> -->
<!--           		<styled-check-box model="user.hasAccess" name="user_{{$index}}" label="{{attribute.fields[universal.value]}}"></styled-check-box> -->
<!--           	</div> -->
          	
            <ul class="location-field-list-display">
              <li class="" ng-repeat="universal in universals track by $index" ng-if="attribute.fields[universal.value] != null && attribute.fields[universal.value] != 'EXCLUDE'">
                <i class="fa fa-check-square"></i>{{attribute.fields[universal.value]}}            
              </li>
            </ul>   
        </div>
        
<!--         <ul> -->
<!--           <li ng-repeat="id in sheet.attributes.ids" ng-init="attribute = sheet.attributes.values[id]"> -->
<!--             {{attribute.label}}    -->
<!--             <span>             -->
<!--               <a href="#" ng-click="ctrl.remove(attribute)">Remove</a> -->
<!--               <a href="#" ng-click="ctrl.edit(attribute)">Edit</a> -->
<!--             </span>           -->
<!--             <ul> -->
<!--               <li ng-repeat="universal in universals" ng-if="attribute.fields[universal.value] != null && attribute.fields[universal.value] != 'EXCLUDE'"> -->
<!--                 {{attribute.fields[universal.value]}}             -->
<!--               </li> -->
<!--             </ul>        -->
<!--           </li>     -->
<!--         </ul> -->
        
      </div> <!-- end row-holder -->
    </div> <!-- end holder -->
  </div> <!-- end ng-if -->
</div>
