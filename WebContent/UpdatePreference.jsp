<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, CrimeFreeBooking.dal.*, CrimeFreeBooking.model.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
	
<title>Crime Free Booking</title>
</head>
<body>
      <style>
		body {
    		background-color: #ccebff;
		}
  	  <div class="jumbotron">
  	  </style>
      <font size="5" color="#341D8F"><strong>Update Your Preferences</strong></font>
      <br>
      <img src="https://image.flaticon.com/icons/svg/39/39383.svg" width="150" height="150" alt="Configuración personal símbolo de seo en contorno para la interfaz icono gratis" title="Configuración personal símbolo de seo en contorno para la interfaz icono gratis">
      <hr>
      <form action="UpdatePreference" method="post">
      	<span class="label label-primary">UserName</span>:   	
      	<%  
      		String id = request.getParameter("id");
      		PreferencesDao.getInstance().delete(Integer.parseInt(id));
		    String username = request.getParameter("username");
      		out.println("<input name=\"username\" type=\"text\" value=\"" + username + "\"");
		%>
		
		<br>
		<br>
      	<span class="label label-primary">Preferred Bathroom Number</span> ：<input type="text" name="bathroom"/><br>
      	<span class="label label-primary">Preferred Bedroom Number</span>：<input type="text" name="bedroom"/><br>
        <br>
        <input type="submit" class="btn btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span class="alert alert-info" role="alert" id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>