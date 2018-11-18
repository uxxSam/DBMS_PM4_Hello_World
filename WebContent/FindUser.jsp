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
    <font size="5" color="#341D8F" ><strong>Lookup Users by User Name</strong></font>
    <br>
	<img src="https://image.flaticon.com/icons/svg/126/126307.svg" width="150" height="150" alt="Binoculares icono gratis" title="Binoculares icono gratis">    <br>
      <hr>
      <form action="FindUsers" method="get">
      	<span class="label label-primary">UserName</span> ：<input type="text" name="username"/>
        &emsp;
        <input type="submit" class="btn btn-sm btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
        <table border="1">
            <tr>
                <th> <span class="label label-default">UserName</span> </th>
                <th> <span class="label label-default">Password</span> </th>
                <th> <span class="label label-primary">FirstName</span> </th>
                <th> <span class="label label-primary">LastName</span> </th>
                <th> <span class="label label-success">Street1</span> </th>
                <th> <span class="label label-success">Street2</span> </th>
                <th> <span class="label label-info">City</span> </th>
                <th> <span class="label label-info">State</span> </th>
                <th> <span class="label label-info">ZipCode</span> </th>
                <th> <span class="label label-warning">Country</span> </th>
            </tr>
            <c:forEach items="${user}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getUserPassword()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getStreet1()}" /></td>
                    <td><c:out value="${user.getStreet2()}" /></td>
                    <td><c:out value="${user.getCity()}" /></td>
                    <td><c:out value="${user.getState()}" /></td>
                    <td><c:out value="${user.getZipCode()}" /></td>
                    <td><c:out value="${user.getCountry()}" /></td>
                </tr>
            </c:forEach>
       </table>
      <br><br><br>
      <a href="login.jsp"><font size="3" class="btn btn-sm btn-danger">Back</font></a>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>