<%@ page language="java" import="java.util.*, CrimeFreeBooking.dal.*, CrimeFreeBooking.model.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>CrimeFreeBooking</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  </head>
  <body>
    <center>
    <%
     String name = new String(request.getParameter("username").getBytes("8859_1"));
     out.println("Welcome:" + name);
    %><br>
    <a href="login.jsp">Log out</a>
    </center>
    
    <font size="10" color="#000" >Find Listings by Price</font>
    <hr>
      <form action="FindListingByPrice" method="get">
        UserName:   	
      	<%  
		    String username = request.getParameter("username");
      		out.println("<input name=\"username\" type=\"text\" value=\"" + name + "\"");
		%>
		<br>
		<br>
      	Maximum Accepted Price ：<input type="text" name="price"/><br>
        <input type="submit" />
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Street1</th>
                <th>Street2</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>Property Type</th>
                <th>Room Type</th>
                <th>Bathrooms</th>
                <th>Bedrooms</th>
                <th>Bedrooms</th>
            </tr>
            <c:forEach items="${listing}" var="listing" >
                <tr>
					<td><c:out value="${listing.getTitle()}" /></td>
					<td><c:out value="${listing.getPrice()}" /></td>
					<td><c:out value="${listing.getStreet1()}" /></td>
					<td><c:out value="${listing.getStreet2()}" /></td>
					<td><c:out value="${listing.getCity()}" /></td>
					<td><c:out value="${listing.getState()}" /></td>
					<td><c:out value="${listing.getZipCode()}" /></td>
					<td><c:out value="${listing.getPropertyType()}" /></td>
					<td><c:out value="${listing.getRoomType()}" /></td>
					<td><c:out value="${listing.getBathrooms()}" /></td>
					<td><c:out value="${listing.getBedrooms()}" /></td>
					<td><c:out value="${listing.getBeds()}" /></td>
                </tr>
            </c:forEach>
       </table>
      <br>

    <br>
    <font size="10" color="#000" >My WishList</font>
    <%
    Users requestedUser = UsersDao.getInstance().getUserByUserName(name);
    List<Wishlists> myWishList = WishlistsDao.getInstance().getWishlistsForUser(requestedUser);
    
    if (myWishList.isEmpty()) {
    	out.println("<br>Empty wishlist");
    } else {
    	for (Wishlists wish : myWishList) {
    		out.println("<br>" + wish.toString());
    	}
    }
    %>
    
    <br>
    <br>
    <br>
    
    <font size="10" color="#000" >My Preference</font>
    <%
    List<Preferences> myPreference = PreferencesDao.getInstance().getPreferenceOfUser(requestedUser);
    
    if (myPreference.isEmpty()) {
    	out.println("<br>Empty preference" + "<a href=\"/DBMS_PM4_Hello_World/create-preference?username=" + name + "\"><input type=\"button\" name=\"\" value=\"Create Preference\"></a>");
    } else {
    	for (Preferences pref : myPreference) {
    		out.println("<br>" + pref.toString());
    	}
    	System.out.println(myPreference.get(0).getPreferenceId());
    	out.println("<a href=\"/DBMS_PM4_Hello_World/DeletePreference?id=" + myPreference.get(0).getPreferenceId() + "\"><input type=\"button\" name=\"\" value=\"Delete Preference\"></a>");
    }
    %>
    
  </body>
</html>