<%-- 
    Document   : index
    Created on : Oct 3, 2025, 3:10:11 PM
    Author     : Guang Trump
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>RANDOM PROGRAM</h1>
        <form method="GET" action="random">
            Number: <input type="number" name="number" value="" />
            <input type="submit" value="submit" />
            <<h2 style="color:red">${requestScope.message}</h2>
        </form>
    </body>
</html>
