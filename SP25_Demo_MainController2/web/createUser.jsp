<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="users?action=list">List All Users</a>
    </h2>
</center>
<div align="center">
    <form method="post" action="users?action=insert">
        <table border="1" cellpadding="5">
            <caption><h2>Add New User</h2></caption>
            <tr><th>Username:</th><td><input type="text" name="username" required/></td></tr>
            <tr><th>Email:</th><td><input type="email" name="email" required/></td></tr>
            <tr><th>Country:</th><td><input type="text" name="country"/></td></tr>
            <tr><th>Role:</th><td><input type="text" name="role" value="User"/></td></tr>
            <tr>
                <th>Status:</th>
                <td>
                    <select name="status">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </td>
            </tr>
            <tr><th>Password:</th><td><input type="password" name="password" required/></td></tr>
            <tr><td colspan="2" align="center"><input type="submit" value="Save"/></td></tr>
        </table>
    </form>
</div>
</body>
</html>
