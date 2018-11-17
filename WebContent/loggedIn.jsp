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
            	<th>Options</th>
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
                <th>Beds</th>
            </tr>
            <c:forEach items="${listing}" var="listing" >
                <tr>
                	<td>
                		<a href="CreateWishList?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listing.getListingId()}"/>"><button>Add to Wishlist</button></a>
                		<a href="CreateRecommendation?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listing.getListingId()}"/>"><button>Recommend</button></a>
                		<button>Safety Analysis</button>
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
       </table>
      <br>

    <br>
    
    <font size="10" color="#000" >Find Listings by Bedroom and Bathroom Numbers</font>
    <hr>
      <form action="FindListingsByBB" method="get">
        UserName:   	
      	<%  
      		out.println("<input name=\"username\" type=\"text\" value=\"" + name + "\"");
		%>
		<br>
		<br>
      	Minimum Bathroom Number ：<input type="text" name="bathroom"/><br>
      	Minimum Bedroom Number ：<input type="text" name="bedroom"/><br>
        <input type="submit" />
      </form>
      <br>
      <c:forEach items="${messagesPriceByBB}" var="messagesPriceByBB" >
     	 <span id="successMessage"><b>${messagesPriceByBB}</b></span>
	  </c:forEach>
      <br>
        <table border="1">
            <tr>
            	<th>Options</th>
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
            <c:forEach items="${listingByBB}" var="listingByBB" >
                <tr>
                	<td>
                		<a href="CreateWishList?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listingByBB.getListingId()}"/>"><button>Add to Wishlist</button></a>
                		<a href="CreateRecommendation?username=<c:out value="${param.username}"/>&listingId=<c:out value="${listingByBB.getListingId()}"/>"><button>Recommend</button></a>
                		<button>Safety Analysis</button>
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
       </table>
      <br>

    <br>
    
    <font size="10" color="#000" >My WishList</font>
    <hr>
    <%
    Users requestedUser = UsersDao.getInstance().getUserByUserName(name);
    List<Wishlists> myWishList = WishlistsDao.getInstance().getWishlistsForUser(requestedUser);
    
    if (myWishList.isEmpty()) {
    	out.println("<br>Empty wishlist");
    } else {
    	for (int i = 0; i < myWishList.size(); i++) {
    		Wishlists wish = myWishList.get(i);
    		out.println("<br>" + wish.toString());
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteWishlist?id=" + myWishList.get(i).getWishlistId() + "\"><input type=\"button\" name=\"\" value=\"Delete Wish\"></a><br>");
    	}
    }
    %>
    
    <br>
    <br>
    <br>
    
    <font size="10" color="#000" >My Recommendations</font>
    <hr>
    <%
    List<Recommendations> myRecommendation = RecommendationsDao.getInstance().getRecommendationsByUser(requestedUser);
    
    if (myRecommendation.isEmpty()) {
    	out.println("<br>Empty recommendation");
    } else {
    	for (int i = 0; i < myRecommendation.size(); i++) {
    		Recommendations rec = myRecommendation.get(i);
    		out.println("<br>" + rec.toString());
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteRecommendation?id=" + myRecommendation.get(i).getRecommendationId() + "\"><input type=\"button\" name=\"\" value=\"Delete Recommendation\"></a><br>");
    	}
    }
    %>
    
    <br>
    
    <font size="10" color="#000" >My Reviews</font>
    <hr>
       
    <%
    List<Reviews> myReviews = ReviewsDao.getInstance().getReviewByUserName(name);

    if (myReviews.isEmpty()) {
    	out.println("<br>No Reviews");
    } else {
    	for (int i = 0; i < myReviews.size(); i++) {
    		Reviews review = myReviews.get(i);
    		out.println("<br>" + review.toString());
    		out.println("<a href=\"/DBMS_PM4_Hello_World/DeleteReview?id=" + myReviews.get(i).getReviewId() + "\"><input type=\"button\" name=\"\" value=\"Delete Review\"></a><br>");
    	}
   	}
    %>
       
    <br>
    
    <font size="10" color="#000" >My Preference</font>
    <hr>
    <%
    List<Preferences> myPreference = PreferencesDao.getInstance().getPreferenceOfUser(requestedUser);
    
    if (myPreference.isEmpty()) {
    	out.println("<br>Empty preference" + "<a href=\"/DBMS_PM4_Hello_World/create-preference?username=" + name + "\"><input type=\"button\" name=\"\" value=\"Create Preference\"></a>");
    } else {
    	for (Preferences pref : myPreference) {
    		out.println("<br>" + pref.toString());
    	}
    	// System.out.println(myPreference.get(0).getPreferenceId());
    	out.println("<a href=\"/DBMS_PM4_Hello_World/update-preference?id=" + myPreference.get(0).getPreferenceId() + "&username=" + name + "\"><input type=\"button\" name=\"\" value=\"Update Preference\"></a>");
    	out.println("<a href=\"/DBMS_PM4_Hello_World/DeletePreference?id=" + myPreference.get(0).getPreferenceId() + "\"><input type=\"button\" name=\"\" value=\"Delete Preference\"></a>");
    	out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "\"><input type=\"button\" name=\"\" value=\"Generate Smart Recommendations\"></a>");
    	//System.out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "><button>Generate Smart Recommendations</button></a>");
    	//out.println("<a href=\"/DBMS_PM4_Hello_World/GenerateRecommendation.jsp?bathroom=" + myPreference.get(0).getBathrooms() + "&bedroom=" + myPreference.get(0).getBedrooms() + "><button>Generate Smart Recommendations</button></a>");
   	}
    %>
  </body>
</html>