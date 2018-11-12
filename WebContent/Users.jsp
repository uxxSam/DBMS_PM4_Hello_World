<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crime Free Booking</title>
</head>
<body>
     <h3>Lookup Users by User Name</h3>
      <hr>
      <form action="FindUser" method="get">
      	UserName ：<input type="text" name="username"/><br>
        <input type="submit" />
      </form>
      <br>

      <h3>Create a New User</h3>
      <hr>
      <form action="CreateUser" method="post">
      	UserName ：<input type="text" name="username"/><br>
      	Password ：<input type="text" name="password"/><br>
      	FirstName ：<input type="text" name="firstname"/><br>
      	LastName ：<input type="text" name="lastname"/><br>
      	Street1 ：<input type="text" name="street1"/><br>
      	Street2 ：<input type="text" name="street2"/><br>
      	City ：<input type="text" name="city"/><br>
      	State ：<input type="text" name="state"/><br>
      	ZipCode ：<input type="text" name="zipcode"/><br>
      	Country ：<input type="text" name="country"/><br>
        <input type="submit" />
      </form>
</body>
</html>