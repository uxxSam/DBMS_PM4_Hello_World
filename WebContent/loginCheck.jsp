<%@ page language="java" import="java.util.*,java.sql.*,java.net.*,CrimeFreeBooking.dal.ConnectionManager, CrimeFreeBooking.dal.UsersDao, java.net.*,CrimeFreeBooking.model.*" pageEncoding="utf-8"%>
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
            String user = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");  
            String pwd = request.getParameter("pwd");
            Connection conn = new ConnectionManager().getConnection();
            Users requestedUser = null;
            
            try {
    			requestedUser = UsersDao.getInstance().getUserByUserName(user);
    		} catch (SQLException e) {
    			response.sendRedirect("login.jsp?errNo");
    		}
            
            if (requestedUser != null && requestedUser.getUserPassword().equals(pwd)) {
            	response.sendRedirect("loggedIn.jsp?username="+URLEncoder.encode(user));
            } else {
            	response.sendRedirect("login.jsp?errNo");
            }
     		
            conn.close();
     %>
  </body>
</html>