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
<aside class="aside animated legend-snapable" ng-class="{'expanded' : ctrl.expanded}" id="dashboardMetadata" ng-cloak> 
  <div id="data-panel-toggle-container">
    <i id="data-panel-expand-toggle" ng-click="ctrl.toggle()" class="fa" ng-class="{'fa-angle-double-right' : ctrl.expanded, 'fa-angle-double-left' : !ctrl.expanded}"></i>
  </div>
      
  <div class="nav-bar">
    <div id="dashboard-dropdown" class="sales-menu dropdown">
      <a href="#" class="link-opener dropdown-toggle active" data-toggle="dropdown">{{dashboard.model.label}}</a>
      <ul id="gdb-dashboard-dropdown-menu" class="dropdown-menu" role="menu" aria-labelledby="sales-dropdown">
        <li ng-repeat="da in dashboard.dashboards | orderBy:'label'">
          <a ng-if="dashboard.dashboardId != da.dashboardId" ng-click="dashboard.setDashboardId(da.dashboardId)">{{da.label}}</a>
        </li>
      </ul>
    </div>
      
    <i ng-click="dashboard.openDashboard()" class="fa fa-external-link ico-dashboard-tab" title="<gdb:localize key='dashboardViewer.newDashboardTabTooltip'/>" ></i> 
      
    <!-- Clone dashboard button -->
    <span ng-if="dashboard.canEdit()" id="clone-dashboard">
      <i ng-click="dashboard.cloneDashboard()" class="fa fa-plus ico-dashboard" title="<gdb:localize key='dashboardViewer.newDashboardTooltip'/>" ></i>
    </span>
      
    <span ng-if="dashboard.canEdit()" id="delete-dashboard">
      <i ng-click="dashboard.removeDashboard()" class="fa fa-minus ico-dashboard" title="<gdb:localize key='dashboardViewer.deleteDashboardTooltip'/>" ></i>
    </span>
      
    <i ng-if="dashboard.canEdit()" ng-click="dashboard.editOptions()" id="dashboard-options-btn" class="fa fa-cog ico-dashboard-options" title="<gdb:localize key='dashboardViewer.dashboardOptionsTooltip'/>" ></i>
          
    <a href="/menu" target="_self" class="fa fa-bars opener-drop pull-right" data-toggle="tooltip" data-placement="bottom" title="Menu"></a>
  </div>
    
  <ng-form name="form">    
    <!-- Global geo filter -->
    <location-filter filter="dashboard.model.location" dashboard-id="dashboard.dashboardId"></location-filter>
                                            
    <div class="sales-accortion panel-group" id="type-accordion">
      <type-accordion types="dashboard.model.types" new-layer="dashboard.newLayer(mdAttributeId)"></type-accordion>  
    </div> <!-- END sales-accortion panel-group -->
    
    <div id="filter-buttons-container">
      <a href="#" ng-click="form.$invalid || dashboard.refresh()" ng-disabled="form.$invalid" class="fa fa-refresh filters-button apply-filters-button" title="<gdb:localize key="dashboardViewer.applyFiltersTooltip"/>" data-placement="left""></a>
      <a href="#" ng-click="form.$invalid || dashboard.save(false)" ng-disabled="form.$invalid" class="fa fa-floppy-o filters-button save-filters-button" title="<gdb:localize key="dashboardViewer.saveFiltersTooltip"/>" data-placement="left""></a>
      <a ng-if="dashboard.canEdit()" href="#" ng-click="form.$invalid || dashboard.save(t)"  ng-disabled="form.$invalid" class="icon-dashboard-icons filters-button save-global-filters-button" title="<gdb:localize key="dashboardViewer.saveGlobalFiltersTooltip"/>"></a>
    </div>
  </ng-form>
</aside>