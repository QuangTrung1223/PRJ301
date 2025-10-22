<%-- 
    Document   : login
    Created on : Sep 26, 2025, 8:17:47 AM
    Author     : Guang Trump
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title> 
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            display: flex;
            max-width: 900px;
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
            overflow: hidden;
        }

        .left-panel {
            flex: 1;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .right-panel {
            flex: 1;
            padding: 40px;
        }

        .logo {
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .welcome-text {
            font-size: 1.2rem;
            line-height: 1.6;
        }

        .form-title {
            font-size: 2rem;
            color: #333;
            margin-bottom: 30px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }

        .form-group input {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            outline: none;
            border-color: #667eea;
        }

        .remember-forgot {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .remember-me {
            display: flex;
            align-items: center;
        }

        .remember-me input {
            margin-right: 5px;
        }

        .forgot-password {
            color: #667eea;
            text-decoration: none;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
            color: #666;
        }

        .register-link a {
            color: #667eea;
            text-decoration: none;
            font-weight: 500;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column;
            }
            
            .left-panel {
                padding: 30px 20px;
            }
            
            .right-panel {
                padding: 30px 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <div class="logo">LOGO</div>
            <div class="welcome-text">
                <h2>Chào mừng trở lại!</h2>
                <p>Hãy đăng nhập để tiếp tục sử dụng dịch vụ của chúng tôi</p>
            </div>
        </div>
        
        <div class="right-panel">
            <h2 class="form-title">Đăng Nhập</h2>
            
            
            
            
           <%
                    //Đọc các COOKIE đang có trong trình duyệt
                    //Nếu COOKIE tồn tại thì xóa biến rỗng để đổi VALUE khác
                    String usernameCookieSaved = "";
                    String passwordCookieSaved = "";
                    //Lụm tất cả các COOKIE có trong trình duyệt gắn cho cookieListFromBrowser
                    Cookie[] cookieListFromBrowser = request.getCookies();

                    if(cookieListFromBrowser != null){
                        for(Cookie cookie : cookieListFromBrowser){
                            if(cookie.getName().equals("COOKIE_USERNAME")) {
                                usernameCookieSaved = cookie.getValue();
                 }
                 if(cookie.getName().equals("COOKIE_PASSWORD")) {
                                passwordCookieSaved = cookie.getValue();
                 }
               }
             }

           %>
            
            
            
            
            
            
            
            
            
            
            
            <c:if test="${requestScope.error != null}">
                <h1 style = "color:purple">${requestScope.error}</h1>
                
                
            </c:if>
            <form action="login" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập hoặc Email</label>
                    <input type="text" id="username" name="username" value ="<%= usernameCookieSaved %>" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password" value ="<%= passwordCookieSaved %>" required>
                </div>
                
                <div class="remember-forgot">
                    <div class="remember-me">
                        <input type="checkbox" id="rememberMe" name="rememberMe">
                        <label for="rememberMe">Ghi nhớ đăng nhập</label>
                    </div>
                    <a href="#" class="forgot-password">Quên mật khẩu?</a>
                </div>
                
                <button type="submit" class="btn">Đăng Nhập</button>
                
                <div class="register-link">
                    <p>Chưa có tài khoản? <a href="register.html">Đăng ký ngay</a></p>
                </div>
            </form>
        </div>
    </div>

  
</body>
</html>
