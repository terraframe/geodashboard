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
  <modal-dialog modal="uploader-div" ng-if="show">
    <div role="dialog" class="ng-modal-content modal-content" style="display: none;" show-on-ready>
      <div class="heading">
        <h1 class="ui-dialog-title"><gdb:localize key="dataUploader.title"/></h1>
      </div>
      <form name="form" class="modal-form">
        <div ng-if="errors.length > 0" class="error-container">
          <div class="label-holder">
            <strong style="color: #8c0000;"><gdb:localize key='dashboard.errorsLabel'/></strong>
          </div>
          <div class="holder">
            <div ng-repeat="error in errors">
              <p class="error-message">{{error}}</p>
            </div>
          </div>
        </div>
        <div class="" style="">
          <fieldset class="">
            <section class="form-container">
              <name-page ng-if="page.current == 'INITIAL'"></name-page>
              <attributes-page ng-if="page.current == 'FIELDS'"></attributes-page>
              <location-page ng-if="page.current == 'LOCATION'"></location-page>
              <coordinate-page ng-if="page.current == 'COORDINATE'"></coordinate-page>
              <summary-page ng-if="page.current == 'SUMMARY'"></summary-page>
            </section>            
          </fieldset>
          <div class="row-holder" >
            <div class="label-holder"></div>
            <div class="holder">
              <div class="button-holder" fire-on-ready>
                <input
                  ng-if="page.current != 'INITIAL'"      
                  type="button"
                  value="<gdb:localize key="dataUploader.previous"/>"
                  class="btn btn-primary" 
                  ng-click="ctrl.prev()"
                  ng-disabled="busy"
                />
                <input
                  ng-if="page.current != 'SUMMARY'"      
                  type="button"
                  value="<gdb:localize key="dataUploader.next"/>"
                  class="btn btn-primary" 
                  ng-click="ctrl.next()"
                  ng-disabled="form.$invalid || busy"
                />
                <input 
                  ng-if="page.current == 'SUMMARY'"
                  type="button"
                  value="<gdb:localize key="dataUploader.import"/>"
                  class="btn btn-primary" 
                  ng-click="ctrl.persist()"
                  ng-disabled="form.$invalid || busy"
                />      
                <input
                  type="button"
                  value="<gdb:localize key="dashboard.Cancel"/>"
                  class="btn btn-default" 
                  ng-click="ctrl.cancel()"
                  ng-disabled="busy"                  
                />
              </div>
            </div>
          </div>          
        </div>
      </form>
    </div>
  </modal-dialog>       
</div>