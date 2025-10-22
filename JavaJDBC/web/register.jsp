<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h2>Register New Member</h2>

      
        <c:if test="${not empty msg}">
            <p style="color:red;">${msg}</p>
        </c:if>

        <form action="auth" method="POST">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="pass" required></td>
                </tr>
                <tr>
                    <td>Full Name:</td>
                    <td><input type="text" name="fname" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="type" value="REGISTER">
                    </td>
                </tr>
            </table>
        </form>
    <h2 style="color: red">${requestScope.msg} </h2>
    </body>
</html>
