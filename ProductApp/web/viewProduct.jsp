<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Product" %>
<html>
<head><title>Product List</title></head>
<body>
<h2>Product List</h2>

<a href="ProductServlet?action=add">Add New Product</a><br><br>

<table border="1" cellpadding="5">
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Quantity</th></tr>
    <%
        List<Product> list = (List<Product>) request.getAttribute("products");
        for (Product p : list) {
    %>
        <tr>
            <td><%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <td><%=p.getPrice()%></td>
            <td><%=p.getQuantity()%></td>
        </tr>
    <% } %>
</table>
</body>
</html>
