<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="users?action=create">Add New User</a>
    </h2>

    <!-- Search form -->
    <form action="users" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="keyword" placeholder="Search user..."/>
        <input type="submit" value="Search"/>
    </form>
</center>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Country</th>
            <th>Role</th>
            <th>Status</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.country}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td>
                    <a href="users?action=edit&id=${user.id}">Edit</a> |
                    <a href="users?action=delete&id=${user.id}"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
