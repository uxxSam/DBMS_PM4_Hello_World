<%@ page language="java" import="java.util.*,java.sql.*,java.net.*,CrimeFreeBooking.dal.ConnectionManager, CrimeFreeBooking.dal.ListingsDao, java.net.*,CrimeFreeBooking.model.*" pageEncoding="utf-8"%>
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
            
            out.println("<font size=\"10\" color=\"#000\">Here are the top 10 recommended listings based on your preference: </font><br>");
     		
           for (int i = 0; i < Math.min(10, listings.size()); i++) {
        	   out.println(listings.get(i).toString());
        	   out.println("<br><br>");
           }
     %>
  </body>
</html>