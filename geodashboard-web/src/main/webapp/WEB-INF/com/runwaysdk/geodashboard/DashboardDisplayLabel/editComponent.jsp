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
<c:set var="page_title" scope="request" value="Edit an existing "/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.DashboardDisplayLabel.form.id" name="com.runwaysdk.geodashboard.DashboardDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="com.runwaysdk.geodashboard.DashboardDisplayLabel.form.update.button" value="Update" action="com.runwaysdk.geodashboard.DashboardDisplayLabelController.update.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.DashboardDisplayLabel.form.delete.button" value="Delete" action="com.runwaysdk.geodashboard.DashboardDisplayLabelController.delete.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.DashboardDisplayLabel.form.cancel.button" value="Cancel" action="com.runwaysdk.geodashboard.DashboardDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
