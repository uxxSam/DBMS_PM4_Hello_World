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
      <font size="5" color="#341D8F"><strong>Create review for listing</strong></font>
      <br>
	  <img src="https://image.flaticon.com/icons/svg/1217/1217973.svg" width="150" height="150" alt="Charlar icono gratis" title="Charlar icono gratis">      <hr>
      <form action="CreateReview" method="post">
      	<span class="label label-primary">UserName</span>:   	
      	<%  
		    String username = request.getParameter("username");
      		out.println("<input name=\"username\" type=\"text\" value=\"" + username + "\"");
		%>
		
		<br>
		<br>
		
		<span class="label label-primary">Listing ID</span>:   	
      	<%  
	    	String listingId = request.getParameter("listingid");
			out.println("<input name=\"listingid\" type=\"text\" value=\"" + listingId + "\"");
		%>

		<br>
		<br>
      	<span class="label label-primary">Review Content</span> ：<input type="text" name="content"/><br>
        <br>
        <input type="submit" class="btn btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span class="alert alert-info" role="alert" id="successMessage"><b>${messages}</b></span>
     	       <a href="login.jsp"><font size="3" class="btn btn-sm btn-danger">Log Out</font></a>
	  </c:forEach>
      <br>
      <br>

      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>