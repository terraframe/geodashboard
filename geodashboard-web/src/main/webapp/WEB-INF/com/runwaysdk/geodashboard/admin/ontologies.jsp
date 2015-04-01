<%--

    Copyright (c) 2013 TerraFrame, Inc. All rights reserved.

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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/geodashboard.tld" prefix="gdb"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<head>

<gdb:localize var="page_title" key="ontologies.title"/>

<!-- Ontologies CSS -->
<jwr:style src="/bundles/termtree.css" useRandomParam="false"/>  

<!-- Ontologies Javascript -->
<jwr:script src="/bundles/runway-controller.js" useRandomParam="false"/>
<jwr:script src="/bundles/termtree.js" useRandomParam="false"/>
<jwr:script src="/bundles/ontology.js" useRandomParam="false"/>

<script type="text/javascript">${js}</script>

</head>

<div id="ontologies"></div>

<script type="text/javascript">
// var $tree = document.getElementById('main-content-frame').contentWindow.document.universaltree.getImpl();

  com.runwaysdk.ui.DOMFacade.execOnPageLoad(function(){
    com.runwaysdk.ui.Manager.setFactory("JQuery");
    
    document.universaltree = new com.runwaysdk.geodashboard.ontology.OntologyTree({
	  termType : "${type}",
	  relationshipTypes : [ "${relationshipType}" ],
      rootTerms : [ {termId : "${rootId}"} ],
      editable : true,
      /* checkable: true, */
      crud: {
        create: { // This configuration gets merged into the jquery create dialog.
          height: 290
        },
        update: {
          height: 290
        }
      }
    });
    document.universaltree.render("#ontologies");
  });
</script>
