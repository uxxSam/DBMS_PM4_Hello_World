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
      <font size="5" color="#341D8F"><strong>Generate Safety Analysis for listing</strong></font>
      <br>
      <img width="150" height="150" class="icon" src="https://image.freepik.com/free-icon/user-settings-interface-symbol-of-a-man-with-a-cogwheel-on-the-head_318-55056.jpg" alt="User settings interface symbol of a man with a cogwheel on the head Free Icon" title="User settings interface symbol of a man with a cogwheel on the head Free Icon" onerror="this.onerror=null;this.src='/broken-image.gif?https%3A%2F%2Fimage.freepik.com%2Ffree-icon%2Fuser-settings-interface-symbol-of-a-man-with-a-cogwheel-on-the-head_318-55056.jpg';ga('send', 'event', 'broken_image', 'detail', '318_729884');">
      <hr>
      <form action="CreateAnalysis" method="post">
      	<span class="label label-primary">Listing ID</span>:   	
      	<%  
		    String listingid = request.getParameter("listingid");
      		out.println("<input name=\"listingid\" type=\"text\" value=\"" + listingid + "\"");
		%>

        <br>
        <br>

		<span class="label label-primary">Radius(m)</span> ：<input type="text" name="radius"/>

        <br>
        <br>
        <input type="submit" class="btn btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
      
      <br>
        <table border="1">
            <tr>
            	<th>Crime Count within Radius</th>
                <th>Most Frequent Crime Type</th>
                <th>Most Frequent Crime Count</th>
                <th>2nd Most Frequent Crime Type</th>
                <th>2nd Most Frequent Crime Count</th>
                <th>3rd Most Frequent Crime Type</th>
                <th>3rd Most Frequent Crime Count</th>
            </tr>
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
       </table>
      <br>
      
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>