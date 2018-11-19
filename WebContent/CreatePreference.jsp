<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <font size="5" color="#341D8F"><strong>Create your preferences</strong></font>
      <br>
      <img src="https://image.flaticon.com/icons/svg/39/39383.svg" width="150" height="150" alt="Configuración personal símbolo de seo en contorno para la interfaz icono gratis" title="Configuración personal símbolo de seo en contorno para la interfaz icono gratis">      <hr>
      <form action="CreatePreference" method="post">
      	<span class="label label-primary">UserName</span>:   	
      	<%  
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
     	 <span id="successMessage" class="alert alert-success" role="alert"><b>${messages}</b></span>
     	 <br><br>
     	 <a href="login.jsp"><font size="3" class="btn btn btn-danger">Log Out</font></a>
	  </c:forEach>
      <br>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>