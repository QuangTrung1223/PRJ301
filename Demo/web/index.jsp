<%-- 
    Document   : index
    Created on : Oct 2, 2025, 9:52:51 PM
    Author     : Guang Trump
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   
    <%
        ArrayList<String> userList = new ArrayList<>();
        userList.add("SonNV01");
        userList.add("FatherNV02");
        userList.add("MotherNV03");
        request.setAttribute("list_user", userList);
    %>

    <body>
        <form action="CongController" method="POST">
            Username:<input type="text" name="username" /> <br>
            Password:<input type="password" name="password" /> <br>
            <input type="submit" value="LOGIN"/>
        </form> 
        
        <!-- Hiển thị thông báo lỗi nếu có -->
        <p style="color:red">${requestScope.MessageError}</p>
        
        <!-- Nếu có messageError thì hiện thêm thông báo chào mừng -->
        <c:if test="${requestScope.MessageError == null}">
            <h1>Chào mừng bạn đến với bình nguyên vô tận</h1>
        </c:if>
            
        <!-- Duyệt list user -->
        <c:forEach var="tay" items="${requestScope.list_user}">
            <h1>${tay}</h1>
        </c:forEach>
    </body>
</html>
