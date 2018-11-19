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
      <font size="5" color="#341D8F"><strong>Generate Safety Analysis for Listing</strong></font>
      <br>
      <img src="https://image.flaticon.com/icons/svg/1/1252.svg" width="150" height="150" alt="Ojo visto a través de lupa icono gratis" title="Ojo visto a través de lupa icono gratis">
      <hr>
      <form action="CreateAnalysis" method="post">
      	<span class="label label-primary">Listing ID</span> :   	
      	<%  
		    String listingid = request.getParameter("listingid");
      		out.println("<input name=\"listingid\" type=\"text\" value=\"" + listingid + "\"");
		%>

        <br>
        <br>

		<span class="label label-primary">Radius(m)</span> ：<input type="text" name="radius"/>

        <br><br>
        <input type="submit" class="btn btn-danger"/>
        <br><br>
      </form>
      <c:forEach items="${messages}" var="messages" >
     	 <span class="alert alert-info" role="alert" id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
      
      <br>
       <div class="col-md-6">
          <table class="table">
            <thead>
            <tr>
            	<th><span class="label label-default"> Crime Count within Radius</span></th>
                <th><span class="label label-primary"> Most Frequent Crime Type</span></th>
                <th><span class="label label-primary"> Most Frequent Crime Count</span></th>
                <th><span class="label label-success"> 2nd Most Frequent Crime Type</span></th>
                <th><span class="label label-success"> 2nd Most Frequent Crime Count</span></th>
                <th><span class="label label-info"> 3rd Most Frequent Crime Type</span></th>
                <th><span class="label label-info"> 3rd Most Frequent Crime Count</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${crime}" var="crime" >
                <tr>
					<td><c:out value="${crime.get(0)}" /></td>
					<td><c:out value="${crime.get(1)}" /></td>
					<td><c:out value="${crime.get(2)}" /></td>
					<td><c:out value="${crime.get(3)}" /></td>
					<td><c:out value="${crime.get(4)}" /></td>
					<td><c:out value="${crime.get(5)}" /></td>
					<td><c:out value="${crime.get(6)}" /></td>
                </tr>
            </c:forEach>
            </tbody>
       </table>
       </div>
      <br>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>