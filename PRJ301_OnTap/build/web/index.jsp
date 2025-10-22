<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Máy Tính Phương Trình Bậc 2</title>
</head>
<body>
    
    <a href="manager">Quản Lí Học Sinh</a>

    <h1>Máy Tính Phương Trình Bậc 2</h1>
    <form action="cal" method="get">
        a: <input type="number" name="a" value="" /> <br>
        b: <input type="number" name="b" value="" /> <br>
        c: <input type="number" name="c" value="" /> <br>
        <input type="submit" value="CALCULATE" />    
    </form>

    <c:if test="${data != null}">
        <h2 style="color: red">${data.msg}</h2>
    </c:if>
        <c:choose>
            <c:when test="${data.info.flag == 1}">
                <h3>x1 = x2 = ${data.info.x1}</h3>
            </c:when>
            <c:when test="${data.info.flag == 2}">
                <h3>x1 = ${data.info.x1}, x2 = ${data.info.x2}</h3>
            </c:when>
            <c:when test="${data.info.flag == 3}">
                <h3>x = ${data.info.x1}</h3>
            </c:when>
            <c:otherwise>
                <h3>Phương trình không có nghiệm</h3>
            </c:otherwise>
        </c:choose>


</body>
</html>
