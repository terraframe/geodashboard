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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View a Greater Than"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan.form.id" name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="comparisonValue">
        ${item.comparisonValue}
      </mjl:dt>
      <mjl:dt attribute="styleReference">
        ${item.styleReference.keyName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan.form.edit.button" value="Edit" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan.viewAll.link" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanController.viewAll.mojo">
  View All
</mjl:commandLink>
