<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Add Product</title></head>
<body>
<h2>Add New Product</h2>
<form action="ProductServlet" method="post">
    Name: <input type="text" name="name"/><br><br>
    Price: <input type="text" name="price"/><br><br>
    Quantity: <input type="text" name="quantity"/><br><br>
    <input type="submit" value="Add Product"/>
</form>
</body>
</html>
