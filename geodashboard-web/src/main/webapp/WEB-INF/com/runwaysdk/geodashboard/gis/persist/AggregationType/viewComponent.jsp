<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View a Aggregation Type"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.persist.AggregationType.form.id" name="com.runwaysdk.geodashboard.gis.persist.AggregationType.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="enumName">
        ${item.enumName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.AggregationType.form.edit.button" value="Edit" action="com.runwaysdk.geodashboard.gis.persist.AggregationTypeController.edit.mojo" />
  </mjl:form>
</dl>
<dl>
</dl>
<mjl:commandLink name="com.runwaysdk.geodashboard.gis.persist.AggregationType.viewAll.link" action="com.runwaysdk.geodashboard.gis.persist.AggregationTypeController.viewAll.mojo">
  View All
</mjl:commandLink>