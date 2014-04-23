<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Edit an existing Style"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.DashboardStyle.form.id" name="com.runwaysdk.geodashboard.gis.DashboardStyle.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="com.runwaysdk.geodashboard.gis.DashboardStyle.form.update.button" value="Update" action="com.runwaysdk.geodashboard.gis.DashboardStyleController.update.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.DashboardStyle.form.delete.button" value="Delete" action="com.runwaysdk.geodashboard.gis.DashboardStyleController.delete.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.DashboardStyle.form.cancel.button" value="Cancel" action="com.runwaysdk.geodashboard.gis.DashboardStyleController.cancel.mojo" />
  </mjl:form>
</dl>
