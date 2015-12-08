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

<div class="fill-block" ng-class="clazz">
  <strong class="title"><gdb:localize key="DashboardThematicLayer.form.fill"/></strong>
  <div class="cell-holder">
    <div class="cell">
      <span><gdb:localize key="DashboardLayer.form.minFill"/></span>
      <div class="color-holder">
        <a href="#" class="color-choice" color-picker model="minFill" element='#modal01'>
          <span class="ico" style="background:{{minFill}}">icon</span>
          <span class="arrow">arrow</span>
        </a>
      </div>
    </div>
    <div class="cell">
      <span><gdb:localize key="DashboardLayer.form.maxFill"/></span>
      <div class="color-holder">
        <a href="#" class="color-choice" color-picker model="maxFill" element='#modal01'>
          <span class="ico" style="background:{{maxFill}};">icon</span>
          <span class="arrow">arrow</span>
        </a>
      </div>
    </div>
    <div class="cell opacity">
      <label><gdb:localize key="DashboardLayer.form.opacity"/></label>
      <div class="text">
        <select class="tab-select" ng-options="ctrl.getFormattedInt(n) for n in [] | decimalrange:0:101 track by n"  ng-model="opacity"></select>
      </div>
    </div>
  </div>
</div>