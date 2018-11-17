<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title><strong>CrimeFreeBooking</strong></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 
  <body>
    <style>
		body {
    		background-color: #ccebff;
		}
  	<div class="jumbotron">
  	</style>
  	

    <center>
    <font size="5" color="#341D8F" >Welcome to CrimeFreeBooking</font>
    <br>
    <img class="irc_mi" src="https://kathleenhalme.com/images/neighbors-clipart-neighbourhood-watch-2.png" onload="typeof google==='object'&amp;&amp;google.aft&amp;&amp;google.aft(this)" width="250" height="250" style="margin-top: 30px;" alt="Related image">
    <br> 
    
    <font size="5" color="#341D8F" >Please Login</font>
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
      <table width="350" height = "180" border="5" bordercolor="#2464B1"> 
 		<tr>
 		  <th>&emsp;Username：</th>
 		  <td><input type="text" name="name" value = "Enter your username" maxlength = "16" onfocus = "if(this.value == 'Enter your username') this.value =''"></td>
 	    </tr>
 	    <tr>
 		  <th>&emsp;Password：</th>
 		  <td><input type="password" name="pwd" maxlength = "20"></td>
 	    </tr>
 	    <tr>
 	      <td colspan = "2" align = "center">
 		    <input type="submit" name="submit" value="Login" class="btn btn btn-primary">
 		    <a href="/DBMS_PM4_Hello_World/create-user">
 		    <input type="button" name="" value="Register" class="btn btn-success">
 		    </a>
 		    <a href="/DBMS_PM4_Hello_World/find-user">
 		    <input type="button" name="findUser" value="Find User" class="btn btn-info">
 		    </a>
 	      </td>
 	    </tr>
 	  </table>
 	</form>
  </center>
  
  </div>
  
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  </body>
</html>