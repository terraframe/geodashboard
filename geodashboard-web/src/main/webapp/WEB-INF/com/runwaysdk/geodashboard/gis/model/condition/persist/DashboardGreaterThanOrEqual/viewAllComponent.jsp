<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View all Greater Than Or Equal"/>
<mjl:table var="item" query="${query}">
  <mjl:context action="com.runwaysdk.geodashboard.gis.model.condition.persist.DashboardGreaterThanOrEqualController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="comparisonValue">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="styleReference">
      <mjl:row>
        ${item.styleReference.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="com.runwaysdk.geodashboard.gis.model.condition.persist.DashboardGreaterThanOrEqualController.view.mojo">
          View
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="DashboardGreaterThanOrEqualController.newInstance" action="com.runwaysdk.geodashboard.gis.model.condition.persist.DashboardGreaterThanOrEqualController.newInstance.mojo">
  Create a new Greater Than Or Equal
</mjl:commandLink>