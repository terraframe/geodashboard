<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Edit an existing Greater Than Or Equal"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqual.form.id" name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqual.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqual.form.update.button" value="Update" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqualController.update.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqual.form.delete.button" value="Delete" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqualController.delete.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqual.form.cancel.button" value="Cancel" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThanOrEqualController.cancel.mojo" />
  </mjl:form>
</dl>
