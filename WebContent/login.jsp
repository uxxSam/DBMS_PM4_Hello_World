<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
 
  <body>
    <center>
    <font size="5" color="#000" >Welcome to CrimeFreeBooking</font>
    <br>
  	<font size="5" color="#000" >Please Login</font>
  	<%  
    String flag = request.getParameter("errNo");  
    try{
         if(flag!=null)
            out.println("<br>Wrong Username password combiantion!\n");
    }catch(Exception e){
        e.printStackTrace();
    }
   %>
  	<form action = "loginCheck.jsp" method="post">
      <table width="300" height = "180" border="5" bordercolor="#A0A0A0"> 
 		<tr>
 		  <th>Username：</th>
 		  <td><input type="text" name="name"  value = "Enter your username" maxlength = "16" onfocus = "if(this.value == 'Enter your username') this.value =''"></td>
 	    </tr>
 	    <tr>
 		  <th>Password：</th>
 		  <td><input type="password" name="pwd" maxlength = "20"></td>
 	    </tr>
 	    <tr>
 	      <td colspan = "2" align = "center">
 		    <input type="submit" name="submit" value="Login">
 		    <a href="/DBMS_PM4_Hello_World/create-user">
 		    <input type="button" name="" value="Register">
 		    </a>
 		    <a href="/DBMS_PM4_Hello_World/find-user">
 		    <input type="button" name="findUser" value="Find User">
 		    </a>
 		    <input type="button" value="Back"
 			  onclick="window.location.href('/webText')">
 	      </td>
 	    </tr>
 	  </table>
 	</form>
  </center>
  </body>
</html>