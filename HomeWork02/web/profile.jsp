

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="constant.NameAttributeConstant"%>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% User user = (User) request.getAttribute(NameAttributeConstant.USER_ATTRIBUTE); %>

    <body>
        
        <h1>Profile</h1>
        FullName:<%= user.getFullname() %> <br>
        UserName:<%= user.getUsername() %> <br>
        Gmail:<%= user.getGmail() %> <br>


    </body>
</html>
