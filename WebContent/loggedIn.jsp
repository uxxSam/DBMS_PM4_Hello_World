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
    
    <!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	
    <title>CrimeFreeBooking</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  </head>
  <body>
     <style>
		body {
    		background-color: #ccebff;
		}
  	<div class="jumbotron">
  	</style>
  	
    <center>
    <br>
    <%
     String name = new String(request.getParameter("username").getBytes("8859_1"));
     out.println("<font size=\"7\" class=\"alert alert-success\" role=\"alert\"> Welcome: " + name + "</font>");
    %><br><br><br>
    <a href="login.jsp"><font size="3" class="alert alert-danger"><u>Log Out</u></font></a>
    <a href="login.jsp"><font size="3" class="alert alert-danger">Back</font></a>
    </center>
    
    <font size="5" color="#341D8F" ><strong>&emsp;Find Listings by Price</strong></font>
    <br>
    <font>&emsp;</font> 
    <img src="https://image.flaticon.com/icons/svg/126/126073.svg" width="150" height="150" alt="Etiqueta del precio icono gratis" title="Etiqueta del precio icono gratis">
      <form action="FindListingByPrice" method="get">
        <strong>&emsp;UserName</strong>:   	
      	<%  
      		out.println("<input name=\"username\" type=\"text\" value=\"" + name + "\"");
		%>
		<br>
		<br>
      	<strong>&emsp;Maximum Accepted Price</strong> ：<input type="text" name="price"/><br>
      	<font>&emsp;</font> 
        <input type="submit" class="btn btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messages}" var="messages" >
     	 <span id="successMessage"><b>${messages}</b></span>
	  </c:forEach>
      <br>
       <div class="col-md-6">
          <table class="table">
            <thead>
            <tr>
            	<th><span class="label label-default">Options</span></th>
                <th><span class="label label-primary">Title</span></th>
                <th><span class="label label-success">Price</span></th>
                <th><span class="label label-success">Street1</span></th>
                <th><span class="label label-success">Street2</span></th>
                <th><span class="label label-success">City</span></th>
                <th><span class="label label-success">State</span></th>
                <th><span class="label label-success">ZipCode</span></th>
                <th><span class="label label-warning">Property Type</span></th>
                <th><span class="label label-warning">Room Type</span></th>
                <th><span class="label label-warning">Bathrooms</span></th>
                <th><span class="label label-warning">Bedrooms</span></th>
                <th><span class="label label-warning">Beds</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listing}" var="listing" >
                <tr>
                	<td>
                		<a href="CreateWishList?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listing.getListingId()}"/>"><button>Add to Wishlist</button></a>
                		<a href="CreateRecommendation?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listing.getListingId()}"/>"><button>Recommend</button></a>
                		<a href="create-analysis?username=<c:out value="${param.username}"/>&listingid=<c:out value="${listing.getListingId()}"/>"><button>Safety Analysis</button></a>
                		<a href="create-review?username=<c:out value="${param.username}"/>&listingid=<c:out value="${listing.getListingId()}"/>"><button>Review Listing</button></a>
                	</td>
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
            </tbody>
       </table>
      <br>

    <br>
    <font size="5" color="#341D8F" ><strong>Find Listings by Bedroom and Bathroom Numbers</strong></font>
    <br>
    <img src="https://image.flaticon.com/icons/svg/14/14804.svg" width="150" height="150" alt="Baño de burbujas icono gratis" title="Baño de burbujas icono gratis">
    <hr>
      <form action="FindListingsByBB" method="get">
        <strong>UserName</strong>:   	
      	<%  
      		out.println("<input name=\"username\" type=\"text\" value=\"" + name + "\"");
		%>
		<br>
		<br>
      	<strong>Minimum Bathroom Number</strong> ：<input type="text" name="bathroom"/><br>
      	<strong>Minimum Bedroom Number</strong> ：<input type="text" name="bedroom"/><br>
        <input type="submit" class="btn btn-danger"/>
      </form>
      <br>
      <c:forEach items="${messagesPriceByBB}" var="messagesPriceByBB" >
     	 <span id="successMessage"><b>${messagesPriceByBB}</b></span>
	  </c:forEach>
      <br>
       <div class="col-md-6">
          <table class="table">
            <thead>
            <tr>
                <th><span class="label label-default">Options</span></th>
                <th><span class="label label-primary">Title</span></th>
                <th><span class="label label-success">Price</span></th>
                <th><span class="label label-success">Street1</span></th>
                <th><span class="label label-success">Street2</span></th>
                <th><span class="label label-success">City</span></th>
                <th><span class="label label-success">State</span></th>
                <th><span class="label label-success">ZipCode</span></th>
                <th><span class="label label-warning">Property Type</span></th>
                <th><span class="label label-warning">Room Type</span></th>
                <th><span class="label label-warning">Bathrooms</span></th>
                <th><span class="label label-warning">Bedrooms</span></th>
                <th><span class="label label-warning">Beds</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listingByBB}" var="listingByBB" >
                <tr>
                	<td>
                		<a href="CreateWishList?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listingByBB.getListingId()}"/>"><button>Add to Wishlist</button></a>
                		<a href="CreateRecommendation?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listingByBB.getListingId()}"/>"><button>Recommend</button></a>
                		<a href="create-analysis?username=<c:out value="${param.username}"/>&listingid=<c:out value="${listingByBB.getListingId()}"/>"><button>Safety Analysis</button></a>
                		<a href="create-review?username=<c:out value="${param.username}"/>&listingid=<c:out value="${listingByBB.getListingId()}"/>"><button>Review Listing</button></a>
                	</td>
					<td><c:out value="${listingByBB.getTitle()}" /></td>
					<td><c:out value="${listingByBB.getPrice()}" /></td>
					<td><c:out value="${listingByBB.getStreet1()}" /></td>
					<td><c:out value="${listingByBB.getStreet2()}" /></td>
					<td><c:out value="${listingByBB.getCity()}" /></td>
					<td><c:out value="${listingByBB.getState()}" /></td>
					<td><c:out value="${listingByBB.getZipCode()}" /></td>
					<td><c:out value="${listingByBB.getPropertyType()}" /></td>
					<td><c:out value="${listingByBB.getRoomType()}" /></td>
					<td><c:out value="${listingByBB.getBathrooms()}" /></td>
					<td><c:out value="${listingByBB.getBedrooms()}" /></td>
					<td><c:out value="${listingByBB.getBeds()}" /></td>
                </tr>
            </c:forEach>
            </tbody>
       </table>
      <br>

    <br>
    
    <font size="5" color="#341D8F" ><strong>My WishList</strong></font>
    <br>
    <img src="https://image.flaticon.com/icons/svg/1146/1146064.svg" width="150" height="150" alt="Carta amorosa icono gratis" title="Carta amorosa icono gratis">
    <hr>
    <%
    Users requestedUser = UsersDao.getInstance().getUserByUserName(name);
    List<Wishlists> myWishList = WishlistsDao.getInstance().getWishlistsForUser(requestedUser);
    
    if (myWishList.isEmpty()) {
    	out.println("<br>Empty wishlist");
    } else {
    	for (int i = 0; i < myWishList.size(); i++) {
    		Wishlists wish = myWishList.get(i);
    	%>
    	<div class="page-header">
	        <h1> <%out.println(wish.toStringTitle());%></h1>
        </div>
          <%
    		out.println("<font color=\"#341D8F\">" + wish.toString() + "</font>");
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteWishlist?id=" + myWishList.get(i).getWishlistId() + "\"><input type=\"button\" name=\"\" class=\"btn btn-danger\" value=\"Delete Wish\"></a>");
    	}
    }
    %>
    
    <br>
    <br>
    <br>
    
    <font size="5" color="#341D8F" ><strong>My Recommendations</strong></font>
    <br>
    <img src="https://image.flaticon.com/icons/svg/654/654076.svg" width="150" height="150" alt="Calidad icono gratis" title="Calidad icono gratis">
    <hr>
    <%
    List<Recommendations> myRecommendation = RecommendationsDao.getInstance().getRecommendationsByUser(requestedUser);
    
    if (myRecommendation.isEmpty()) {
    	out.println("<br>Empty recommendation");
    } else {
    	for (int i = 0; i < myRecommendation.size(); i++) {
    		Recommendations rec = myRecommendation.get(i);
    		out.println("<br><font color=\"#341D8F\">" + (i + 1) + ") " + rec.toString() + "</font>");
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteRecommendation?id=" + myRecommendation.get(i).getRecommendationId() + "\"><input type=\"button\" name=\"\" class=\"btn btn-danger\" value=\"Delete Recommendation\"></a><br>");
    	}
    }
    %>
    
    <br>
    
    <font size="5" color="#341D8F" ><strong>My Reviews</strong></font>
    <br>
    <img src="https://image.flaticon.com/icons/svg/1260/1260162.svg" width="150" height="150" alt="Clasificación icono gratis" title="Clasificación icono gratis">
    <hr>
       
    <%
    List<Reviews> myReviews = ReviewsDao.getInstance().getReviewByUserName(name);

    if (myReviews.isEmpty()) {
    	out.println("<br>No Reviews");
    } else {
    	for (int i = 0; i < myReviews.size(); i++) {
    		Reviews review = myReviews.get(i);
    		out.println("<br><font color=\"#341D8F\">" + (i + 1) + ") " + review.toString() + "</font>");
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteReview?id=" + myReviews.get(i).getReviewId() + "\"><input type=\"button\" name=\"\" class=\"btn btn-danger\" value=\"Delete Review\"></a><br>");
    	}
   	}
    %>
       
    <br>
    
    <font size="5" color="#341D8F" ><strong>My Preferences</strong></font>
    <br>
    <img src="https://image.flaticon.com/icons/svg/62/62297.svg" width="150" height="150" alt="Botón ajustes de carpeta icono gratis" title="Botón ajustes de carpeta icono gratis">
    <hr>
    <%
    List<Preferences> myPreference = PreferencesDao.getInstance().getPreferenceOfUser(requestedUser);
    
    if (myPreference.isEmpty()) {
    	out.println("<br>Empty preference<br><br>" + "<a href=\"/DBMS_PM4_Hello_World/create-preference?username=" + name + "\"><input type=\"button\" name=\"\" class=\"btn btn-danger\" value=\"Create Preference\" class=\"alert alert-success\"></a>");
    } else {
    	for (Preferences pref : myPreference) {
    		out.println("<font size=\"5\" color=\"#341D8F\">" + pref.toString() + "<br></font>");
    	}
    	// System.out.println(myPreference.get(0).getPreferenceId());
    	out.println("<a href=\"/DBMS_PM4_Hello_World/update-preference?id=" + myPreference.get(0).getPreferenceId() + "&username=" + name + "\"><input type=\"button\" name=\"\" class=\"btn btn-primary\" value=\"Update Preferences\"></a>");
    	out.println("<a href=\"/DBMS_PM4_Hello_World/DeletePreference?id=" + myPreference.get(0).getPreferenceId() + "\"><input type=\"button\" name=\"\" class=\"btn btn-danger\" value=\"Delete Preference\"></a>");
    	out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "\"><input type=\"button\" name=\"\"class=\"btn btn-success\"  value=\"Generate Smart Recommendations\"></a>");
    	//System.out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "><button>Generate Smart Recommendations</button></a>");
    	//out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "><button>Generate Smart Recommendations</button></a>");
   	}
    %>
    
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  </body>
</html>