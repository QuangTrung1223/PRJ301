<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>User Detail</title></head>
<body>
<h2>User Detail</h2>
<c:if test="${not empty userDetail}">
    <p>UserID: ${userDetail.userID}</p>
    <p>Username: ${userDetail.userName}</p>
    <p>Role: ${userDetail.role}</p>
</c:if>

<a href="${pageContext.request.contextPath}/login">Logout</a>
</body>
</html>
