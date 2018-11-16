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
     <h3>Lookup Users by User Name</h3>
      <hr>
      <form action="FindUsers" method="get">
      	UserName ：<input type="text" name="username"/><br>
        <input type="submit" />
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>Password</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Street1</th>
                <th>Street2</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>Country</th>
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
      <br>
</body>
</html>