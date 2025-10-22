<%-- 
    Document   : login
    Created on : Oct 4, 2025, 1:08:45 PM
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
        <form action="auth" method="POST">
           
                
                    <tr>
                        <th>Email:</th>
                        <th><input type="text" name="email" value="" /></th>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                    
                </tbody>
            
                    <input type="submit" name="type" value="LOGIN" />
        </form>
    </body>
</html>
