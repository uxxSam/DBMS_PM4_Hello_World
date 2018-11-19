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
     <%
           for (int i = 0; i < Math.min(10, listings.size()); i++) {
        	   out.println(listings.get(i).toString());
        	   out.println("<br><br>");
           }
     %>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  </body>
</html>