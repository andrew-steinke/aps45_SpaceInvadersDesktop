
<%@page import="edu.pitt.is1017.spaceinvaders.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion - Register</title>
        
        
    </head>
    <body>
        <form id="Login" action="RegisterValidator" method="post">
            <label for="txtFirstName">First Name:</label> <input type="text" id="txtFirstName" name="txtFirstName" value="">
            <br />
            <label for="txtLastName">Last Name:</label> <input type="text" id="txtLastName" name="txtLastName" value="">
            <br />
            <label for="txtEmail">Email:</label> <input type="text" id="txtEmail" name="txtEmail" value="">
            <br />
            <label for="txtPassword">Password:</label> <input type="text" id="txtPassword" name="txtPassword" value="">
            <br />
            <label for="txtConfirmPassword">Confirm Password:</label> <input type="text" id="txtConfirmPassword" name="txtConfirmPassword" value="">
            <br />
           
            <input type="submit" id="btnRegister" name="btnRegister" value="Register"></input>
            
        </form>
    </body>
</html>
