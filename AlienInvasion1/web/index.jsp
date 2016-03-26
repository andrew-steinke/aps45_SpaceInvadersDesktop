
<%@page import="edu.pitt.is1017.spaceinvaders.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion - Login</title>
    </head>
    <%
       
    %>
    <body>
        <form id="Login" action="LoginValidator" method="post">
            <label for="txtUserName">User Name:</label> <input type="text" id="txtUserName" name="txtUserName" value="">
            <br />
            <label for="txtPassword">Password:&nbsp;</label> <input type="password" id="txtPassword" name="txtPassword" value="">
            <br />
            <input type="submit" id="btnSubmit" name="btnSubmit" value="Login"></input>
            <a href ="register.jsp">Register</a>
        </form>
    </body>
</html>
