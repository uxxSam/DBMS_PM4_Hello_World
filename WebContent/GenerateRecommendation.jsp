<%@ page language="java" import="java.util.*,java.sql.*,java.net.*,CrimeFreeBooking.dal.ConnectionManager, CrimeFreeBooking.dal.ListingsDao, java.net.*,CrimeFreeBooking.model.*" pageEncoding="utf-8"%>
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
    <%        
            String bathroom = new String(request.getParameter("bathroom"));
            String bedroom = request.getParameter("bedroom");
            Connection conn = new ConnectionManager().getConnection();
            List<Listings> listings = null;
            
            try {
            	listings = ListingsDao.getInstance().getListingsBasedOnBedsAndBaths(Integer.parseInt(bedroom), Integer.parseInt(bathroom));
    		} catch (SQLException e) {
    			out.println("Failed to generate recommendations for you");
    		} finally {
    			conn.close();
    		}
            
            out.println("<font size=\"6\" color=\"#341D8F\"><strong><center>Your Personalized Top 10 Recommended Listings</center></strong></font><br>");
     %>
     <center>
     <img src="https://image.flaticon.com/icons/svg/63/63720.svg" width="150" height="150" alt="Edificio 3D icono gratis" title="Edificio 3D icono gratis">
     </center>
     <br>
     
     <table class="table">
            <thead>
            <tr>
            	<th><span class="label label-default">listingId</span></th>
                <th><span class="label label-primary">URL</span></th>
                <th><span class="label label-success">title</span></th>
                <th><span class="label label-success">price</span></th>
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
                <tr>
                     <%
				           for (int i = 0; i < Math.min(10, listings.size()); i++) {
				        	   out.println("<tr><td>" + listings.get(i).getListingId() + "</td>");
				        	   out.println("<td> <a href=\"" + listings.get(i).getUrl() + "\">" + listings.get(i).getUrl() + "</a></td>");
				        	   out.println("<td>" + listings.get(i).getTitle() + "</td>");
				        	   out.println("<td>" + listings.get(i).getPrice() + "</td>");
				        	   out.println("<td>" + listings.get(i).getStreet1() + "</td>");
				        	   out.println("<td>" + listings.get(i).getStreet2() + "</td>");
				        	   out.println("<td>" + listings.get(i).getCity() + "</td>");
				        	   out.println("<td>" + listings.get(i).getState() + "</td>");
				        	   out.println("<td>" + listings.get(i).getZipCode() + "</td>");
				        	   out.println("<td>" + listings.get(i).getPropertyType() + "</td>");
				        	   out.println("<td>" + listings.get(i).getRoomType() + "</td>");
				        	   out.println("<td>" + listings.get(i).getBathrooms() + "</td>");
				        	   out.println("<td>" + listings.get(i).getBedrooms() + "</td>");
				        	   out.println("<td>" + listings.get(i).getBeds() + "</td></tr>");
				           }
				     %>
                </tr>
            </tbody>
       </table>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  </body>
</html>