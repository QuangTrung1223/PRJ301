

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <c:forEach var="tay" items="${sessionScope.listSession}">
                    <li>${tay.id} - ${tay.name} - ${tay.age}</li>
                </c:forEach>
    </body>
</html>
