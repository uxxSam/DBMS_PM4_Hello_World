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
      <font size="5" color="#341D8F" ><strong>Create a New User</strong></font>
      <br>
      <img src="https://image.flaticon.com/icons/svg/72/72648.svg" width="150" height="150" alt="Nuevo usuario icono gratis" title="Nuevo usuario icono gratis">
      <hr>
      <form action="CreateUser" method="post">
      	<span class="label label-default">UserName</span> ：<input type="text" name="username"/>&emsp;
      	<span class="label label-default">Password</span> ：<input type="text" name="password"/><br>
      	<span class="label label-primary">FirstName</span> ：<input type="text" name="firstname"/>&emsp;
      	<span class="label label-primary">LastName</span> ：<input type="text" name="lastname"/><br>
      	<span class="label label-success">Street1</span> ：<input type="text" name="street1"/><br>
      	<span class="label label-success">Street2</span> ：<input type="text" name="street2"/><br>
      	<span class="label label-success">City</span> ：<input type="text" name="city"/>&emsp;
      	<span class="label label-success">State</span> ：<input type="text" name="state"/><br>
      	<span class="label label-success">ZipCode</span> ：<input type="text" name="zipcode"/>&emsp;
      	<span class="label label-success">Country</span> ：<input type="text" name="country"/><br>
        <br>
        <input type="submit" class="btn btn-danger"/>
        <a href="login.jsp"><font class="btn btn btn-danger">Back</font></a>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span class="alert alert-info" role="alert" id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      
      
      </div>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>