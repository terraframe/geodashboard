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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/geoprism.tld" prefix="gdb"%>
<%@ page import="com.runwaysdk.constants.DeployProperties" %>


<gdb:localize var="page_title" key="splash.pagetitle" />

<% String webappRoot = request.getContextPath() + "/"; %> 

<gdb:localize var="logoalt" key="splash.logoalt" />
<img id="logo" style="max-width:250px;" src="<%out.print(webappRoot);%>net/geoprismet/geoprism/images/splash_logo.png" alt="${logoalt}">

<br/>
<br/>

<header id="header">
  <h1><gdb:localize key="splash.header" /></h1>
</header>

<p><gdb:localize key="splash.powered" /></p>