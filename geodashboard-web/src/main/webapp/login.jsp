<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/geodashboard.tld" prefix="gdb"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title><gdb:localize key="login.title" /></title>
  
  <jwr:style src="/com/runwaysdk/geodashboard/login/login.css" />  
</head>
<body>
  <header id="header">
    <h1><gdb:localize key="login.header" /></h1>
  </header>

  <!-- TODO : registering of users
  <a class="register" href="index.html">Not a member? Register now</a>
  -->
  
  <c:if test="${not empty param.errorMessage}">
    <div class="error-message">
      <p>${param.errorMessage}</p>
    </div>
  </c:if>

  <form method="post" action="session/login" class="login">
		<p>
		  <label> <gdb:localize key="login.username" />: </label>
		  <mjl:input param="username" type="text" id="login" />
		</p>
		<p>
		  <label> <gdb:localize key="login.password" />: </label>
		  <mjl:input param="password" type="password" id="password" value="" />
		</p>

    <p class="login-submit">
      <button type="submit" class="login-button"><gdb:localize key="login.button" /></button>
    </p>

    <!-- TODO: Forgot password and remember me functionality.
    <div>
      <a class="forgot-password" href="index.html">Forgot your password?</a>
      <label class="remember-me">&nbsp;Remember me<input name="rememberme" id="rememberme" type="checkbox" checked="checked" value="forever"/></label>
    </div>
    -->
  </form>
  
</body>
</html>
