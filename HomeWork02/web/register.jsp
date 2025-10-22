

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="constant.NameAttributeConstant" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <meta charset="UTF-8">
    <title>Register account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        h1 {
            font-size: 28px;
        }

        form {
            width: 300px;
        }

        label {
            display: inline-block;
            width: 100px; /* căn thẳng label */
            font-weight: bold;
            margin-bottom: 8px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 180px; /* độ rộng bằng nhau */
            padding: 4px;
            margin-bottom: 8px;
            box-sizing: border-box;
        }

        button {
            margin-left: 100px; /* canh theo label */
            padding: 5px 12px;
        }
    </style>
</head>
<body>
    <h1>Register account</h1>
    <form action="process" method="post">
        <label>FullName:</label>
        <input type="text" name="fname"><br>
        <label>UserName:</label>
        <input type="text" name="username"><br>
        <label>Password:</label>
        <input type="password" name="pwd"><br>
        <label>Gmail:</label>
        <input type="gmail" name="gmail"><br>
        <button type="submit">Đăng Ký</button>
    </form>
        
        <% String strMsg = (String) request.getAttribute(NameAttributeConstant.ERROR_ATTRIBUTE); %>
        <% if (strMsg != null) { %>
        <%= strMsg %>
        <% } %>

        
    </body>
</html>
