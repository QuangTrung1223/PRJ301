<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Product List</title></head>
<body>
<h2>Product List (admin)</h2>

<form action="${pageContext.request.contextPath}/products" method="get">
    <input type="text" name="search" placeholder="Search price < 1000" value="${searchQuery}" />
    <button type="submit">Search</button>
    <a href="${pageContext.request.contextPath}/products">Clear</a>
</form>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>Name</th><th>Price</th><th>Description</th><th>Stock</th><th>Import Date</th>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td>${p.stock}</td>
            <td>${p.importDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
