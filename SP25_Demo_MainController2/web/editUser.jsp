<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="users?action=list">List All Users</a>
    </h2>
</center>
<div align="center">
    <form method="post" action="users?action=update">
        <table border="1" cellpadding="5">
            <caption><h2>Edit User</h2></caption>
            <input type="hidden" name="id" value="${user.id}"/>
            <tr><th>Username:</th><td><input type="text" name="username" value="${user.username}"/></td></tr>
            <tr><th>Email:</th><td><input type="email" name="email" value="${user.email}"/></td></tr>
            <tr><th>Country:</th><td><input type="text" name="country" value="${user.country}"/></td></tr>
            <tr><th>Role:</th><td><input type="text" name="role" value="${user.role}"/></td></tr>
            <tr>
                <th>Status:</th>
                <td>
                    <select name="status">
                        <option value="1" <c:if test="${user.status}">selected</c:if>>Active</option>
                        <option value="0" <c:if test="${!user.status}">selected</c:if>>Inactive</option>
                    </select>
                </td>
            </tr>
            <tr><th>Password:</th><td><input type="text" name="password" value="${user.password}"/></td></tr>
            <tr><td colspan="2" align="center"><input type="submit" value="Save"/></td></tr>
        </table>
    </form>
</div>
</body>
</html>
