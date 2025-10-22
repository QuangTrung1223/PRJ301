<%-- 
    Document   : home
    Created on : Sep 19, 2025, 10:49:15 AM
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
        <c:if test="${data != null}">
        <h2 style="color: red">${data.msg}</h2>
    </c:if>
        <a href="createstd.jsp"> Thêm mới học sinh</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>First name</th>
                    <th>Last Name</th>
                    <th>DOB</th>
                    <th>Gender</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="item" items="${requestScope.data}">
                
            
                
                <tr>
                    <td>${item.getStudentId}</td>
                    <td>${item.getFirstName}</td>
                    <td>${item.getLastName}</td>
                    <td>${item.getDOB}</td>
                    <td>${item.getGender}</td>
                </tr>
                
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
