<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    Username: <input type="text" name="username" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <button type="submit">Login</button>
</form>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>
</body>
</html>
