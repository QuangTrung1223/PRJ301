<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List</title>
    </head>
    <body>
        <h2>Student Management</h2>

        <!-- Button to load student list -->
        <form method="get" action="student">
            <input type="submit" value="Xem list học sinh tại đây" />
        </form>

        <hr/>

        <!-- Show student list -->
        <c:if test="${not empty list}">
            <h3>Danh sách học sinh:</h3>
            <ul>
                
                
                <c:forEach var="tay" items="${sessionScope.listSession}">
                    <li>${tay.id} - ${tay.name} - ${tay.age}</li>
                </c:forEach>
                    
                    <!<!-- requestScope.list         : request.getAtributte("list"); 
                           sessionSCope.listSession   : session.getAtributte("listSession");
                    
                    
                    -->          
                    
                    
                    
                    
                    
                    
            </ul>
        </c:if>

        <c:if test="${empty list}">
            <p>Chưa có học sinh nào.</p>
        </c:if>

        <hr/>

        <!-- JSTL demo -->
        <c:set var="a" value="12" />

        <p>Giá trị của a: ${a}</p>

         <c:choose>
            <c:when test="${a > 20}">
                <p>a lớn hơn 20</p>
            </c:when>
            <c:when test="${a == 20}">
                <p>a bằng 20</p>
            </c:when>
            <c:otherwise>
                <p>a bé hơn 20</p>
            </c:otherwise>
               
        </c:choose>
            
            
            
            
            
            
           

        <hr/>
        <a href="https://youtube.com" target="_blank">Đi tới YouTube</a>
    </body>
</html>
