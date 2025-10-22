<%-- 
    Document   : register
    Created on : Sep 26, 2025, 8:17:15 AM
    Author     : Guang Trump
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            display: flex;
            max-width: 1000px;
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
            overflow: hidden;
        }

        .left-panel {
            flex: 1;
            padding: 40px;
        }

        .right-panel {
            flex: 1;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .logo {
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 20px;
            color: #f5576c;
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
            border-color: #f5576c;
        }

        .name-group {
            display: flex;
            gap: 15px;
        }

        .name-group .form-group {
            flex: 1;
        }

        .terms {
            display: flex;
            align-items: flex-start;
            margin-bottom: 20px;
        }

        .terms input {
            margin-right: 10px;
            margin-top: 3px;
        }

        .terms label {
            font-size: 0.9rem;
            color: #666;
            line-height: 1.4;
        }

        .terms a {
            color: #f5576c;
            text-decoration: none;
        }

        .terms a:hover {
            text-decoration: underline;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
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

        .login-link {
            text-align: center;
            margin-top: 20px;
            color: #666;
        }

        .login-link a {
            color: #f5576c;
            text-decoration: none;
            font-weight: 500;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        .password-requirements {
            font-size: 0.8rem;
            color: #666;
            margin-top: 5px;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column-reverse;
            }
            
            .left-panel {
                padding: 30px 20px;
            }
            
            .right-panel {
                padding: 30px 20px;
            }
            
            .name-group {
                flex-direction: column;
                gap: 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <h2 class="form-title">Đăng Ký Tài Khoản</h2>
            <form id="registerForm" action="RegisterController" method="POST">
                <div class="name-group">
                    <div class="form-group">
                        <label for="firstName">Họ</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Tên</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" name="username" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password" required>
                    <div class="password-requirements">Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số</div>
                </div>
                
                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>
                
                <div class="terms">
                    <input type="checkbox" id="agreeTerms" name="agreeTerms" required>
                    <label for="agreeTerms">Tôi đồng ý với <a href="#">Điều khoản dịch vụ</a> và <a href="#">Chính sách bảo mật</a></label>
                </div>
                
                <button type="submit" class="btn">Đăng Ký</button>
                
                <div class="login-link">
                    <p>Đã có tài khoản? <a href="login.html">Đăng nhập ngay</a></p>
                </div>
            </form>
        </div>
        
        <div class="right-panel">
            <div class="logo">LOGO</div>
            <div class="welcome-text">
                <h2>Chào mừng bạn!</h2>
                <p>Hãy tham gia cùng chúng tôi và khám phá những tiện ích tuyệt vời</p>
            </div>
        </div>
    </div>

   <script>
    document.getElementById('registerForm').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            e.preventDefault();
            alert('Mật khẩu xác nhận không khớp!');
        } else if (password.length < 8) {
            e.preventDefault();
            alert('Mật khẩu phải có ít nhất 8 ký tự!');
        }
    });
</script>

</body>
</html>

