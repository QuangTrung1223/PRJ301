

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MÁY TRÍNH PHƯƠNG TRÌNH BẬC 2</title> <br>
    </head>
    
    <body>
        <%
        String tay = (String) request.getAttribute("WebAppne");
        
        
        %>
        <form action="webApp" method="post">
          Nhập a:  <input type="text" name="a" value="" /> <br>
          Nhập b:  <input type="text" name="b" value="" /> <br>
          Nhập c:  <input type="text" name="c" value="" /> <br>
            <input type="submit" value="submit" />
            
            <select name="Phương trình bậc 2">
                <option disabled>---Vui lòng chọn---</option>
                <<option value="Phương trình có 2 nghiệm phân biệt">text</option>
                
                
            </select>
            
            
          
        </form>
        <h2></h2>
    </body>
</html>
