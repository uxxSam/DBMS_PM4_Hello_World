<%@ page language="java" import="java.util.*, CrimeFreeBooking.dal.*, CrimeFreeBooking.model.*" pageEncoding="UTF-8"%>
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