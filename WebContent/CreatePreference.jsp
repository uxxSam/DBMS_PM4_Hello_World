<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crime Free Booking</title>
</head>
<body>
      <h3>Create your preference</h3>
      <hr>
      <form action="CreatePreference" method="post">
      	UserName:   	
      	<%  
		    String username = request.getParameter("username");
      		out.println("<input name=\"username\" type=\"text\" value=\"" + username + "\"");
		%>
		
		<br>
		<br>
      	Preferred Bathroom Number ：<input type="text" name="bathroom"/><br>
      	Preferred Bedroom Number：<input type="text" name="bedroom"/><br>
        <input type="submit" />
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
</body>
</html>