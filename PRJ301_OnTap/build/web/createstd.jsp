<%-- 
    Document   : createstd
    Created on : Sep 19, 2025, 2:41:00 PM
    Author     : Guang Trump
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>       
        <h1>Thêm 1 học sinh</h1>
        
      <form method="POST" action="manager" >
      Student ID: <input type="number" name="id" value="" />
      First Name: <input type="text" name="fname" value="" />
      Last Name: <input type="text" name="lname" value="" />
      Gender: <input type="number" name="gender" value="" />
      DOB: <input type="datetime" name="dob" value="" />
      <input type="submit" value="Thêm học sinh" />
      </form>
         <c:if test="${data != null}">
        <h2 style="color: red">${data.msg}</h2>
    </c:if>
    
    </body>
</html>
