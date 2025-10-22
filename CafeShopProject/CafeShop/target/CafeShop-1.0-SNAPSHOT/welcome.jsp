<%-- 
    Document   : welcome
    Created on : Sep 26, 2025, 8:19:15 AM
    Author     : Guang Trump
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chào Mừng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            overflow-x: hidden;
        }

        .welcome-container {
            max-width: 1200px;
            width: 90%;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            text-align: center;
            position: relative;
            overflow: hidden;
        }

        .welcome-container::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: radial-gradient(circle, rgba(255,255,255,0.1) 0%, transparent 70%);
            z-index: -1;
        }

        .logo {
            font-size: 3.5rem;
            font-weight: 700;
            margin-bottom: 10px;
            background: linear-gradient(45deg, #fff, #a8edea);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            text-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .welcome-title {
            font-size: 2.8rem;
            margin-bottom: 15px;
            font-weight: 600;
        }

        .welcome-subtitle {
            font-size: 1.3rem;
            margin-bottom: 30px;
            opacity: 0.9;
            max-width: 700px;
            margin-left: auto;
            margin-right: auto;
            line-height: 1.6;
        }

        .user-info {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 40px;
            flex-wrap: wrap;
            gap: 20px;
        }

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: linear-gradient(45deg, #ff9a9e, #fad0c4);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 2.5rem;
            border: 3px solid rgba(255, 255, 255, 0.3);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .user-details {
            text-align: left;
        }

        .username {
            font-size: 1.8rem;
            font-weight: 600;
            margin-bottom: 5px;
        }

        .user-email {
            font-size: 1.1rem;
            opacity: 0.8;
        }

        .features {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            gap: 20px;
            margin-bottom: 40px;
        }

        .feature-card {
            background: rgba(255, 255, 255, 0.15);
            border-radius: 15px;
            padding: 25px;
            width: 250px;
            transition: transform 0.3s, background 0.3s;
            backdrop-filter: blur(5px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }

        .feature-card:hover {
            transform: translateY(-10px);
            background: rgba(255, 255, 255, 0.25);
        }

        .feature-icon {
            font-size: 2.5rem;
            margin-bottom: 15px;
            color: #a8edea;
        }

        .feature-title {
            font-size: 1.3rem;
            margin-bottom: 10px;
            font-weight: 600;
        }

        .feature-desc {
            font-size: 0.95rem;
            opacity: 0.9;
            line-height: 1.5;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
            margin-top: 30px;
        }

        .btn {
            padding: 14px 30px;
            border: none;
            border-radius: 50px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            display: flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
        }

        .btn-primary {
            background: linear-gradient(45deg, #ff9a9e, #fecfef);
            color: #333;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .btn-primary:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
        }

        .btn-secondary {
            background: transparent;
            color: white;
            border: 2px solid rgba(255, 255, 255, 0.5);
        }

        .btn-secondary:hover {
            background: rgba(255, 255, 255, 0.1);
            border-color: rgba(255, 255, 255, 0.8);
        }

        .floating-elements {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            z-index: -1;
        }

        .floating-element {
            position: absolute;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
        }

        .floating-element:nth-child(1) {
            width: 80px;
            height: 80px;
            top: 10%;
            left: 5%;
            animation: float 15s infinite linear;
        }

        .floating-element:nth-child(2) {
            width: 120px;
            height: 120px;
            top: 70%;
            left: 80%;
            animation: float 20s infinite linear reverse;
        }

        .floating-element:nth-child(3) {
            width: 60px;
            height: 60px;
            top: 20%;
            left: 85%;
            animation: float 12s infinite linear;
        }

        @keyframes float {
            0% {
                transform: translate(0, 0) rotate(0deg);
            }
            100% {
                transform: translate(100px, 100px) rotate(360deg);
            }
        }

        .stats {
            display: flex;
            justify-content: space-around;
            margin: 40px 0;
            flex-wrap: wrap;
            gap: 20px;
        }

        .stat-item {
            text-align: center;
        }

        .stat-number {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 5px;
            background: linear-gradient(45deg, #fff, #a8edea);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .stat-label {
            font-size: 1rem;
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .welcome-container {
                padding: 30px 20px;
            }
            
            .logo {
                font-size: 2.8rem;
            }
            
            .welcome-title {
                font-size: 2.2rem;
            }
            
            .welcome-subtitle {
                font-size: 1.1rem;
            }
            
            .features {
                flex-direction: column;
                align-items: center;
            }
            
            .feature-card {
                width: 100%;
                max-width: 300px;
            }
            
            .action-buttons {
                flex-direction: column;
                align-items: center;
            }
            
            .btn {
                width: 100%;
                max-width: 280px;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <div class="floating-elements">
            <div class="floating-element"></div>
            <div class="floating-element"></div>
            <div class="floating-element"></div>
        </div>
        
        <div class="logo">WELCOME</div>
        <h1>Welcome to our system ${sessionScope.session_login} </h1>
        <p class="welcome-subtitle">We glad to see you again.</p>
        
        <div class="user-info">
            <div class="avatar">
                <i class="fas fa-user"></i>
            </div>
            <div class="user-details">
                <div class="username">Nguyễn Lê Quang Trung</div>
                <div class="user-email">qtrunghzz05@gmail.com</div>
            </div> <br>
            <a href="logout" class="logout-link">log out</a>
        </div>
        
        <div class="stats">
            <div class="stat-item">
                <div class="stat-number">15</div>
                <div class="stat-label">Dự án đã tham gia</div>
            </div>
            <div class="stat-item">
                <div class="stat-number">98%</div>
                <div class="stat-label">Hoàn thành công việc</div>
            </div>
            <div class="stat-item">
                <div class="stat-number">4.9</div>
                <div class="stat-label">Đánh giá từ cộng đồng</div>
            </div>
        </div>
        
        <div class="features">
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-rocket"></i>
                </div>
                <h3 class="feature-title">Khởi Đầu Nhanh Chóng</h3>
                <p class="feature-desc">Hướng dẫn nhanh để bạn làm quen với hệ thống trong vòng 5 phút.</p>
            </div>
            
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-chart-line"></i>
                </div>
                <h3 class="feature-title">Theo Dõi Tiến Độ</h3>
                <p class="feature-desc">Theo dõi và phân tích hiệu suất công việc của bạn một cách trực quan.</p>
            </div>
            
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="fas fa-users"></i>
                </div>
                <h3 class="feature-title">Cộng Đồng</h3>
                <p class="feature-desc">Kết nối với hàng ngàn người dùng khác trong cộng đồng của chúng tôi.</p>
            </div>
        </div>
        
        <div class="action-buttons">
            <a href="dashboard.html" class="btn btn-primary">
                <i class="fas fa-tachometer-alt"></i> Bắt Đầu Ngay
            </a>
            <a href="profile.html" class="btn btn-secondary">
                <i class="fas fa-user-cog"></i> Cài Đặt Hồ Sơ
            </a>
            <a href="help.html" class="btn btn-secondary">
                <i class="fas fa-question-circle"></i> Trợ Giúp
            </a>
        </div>
    </div>

    <script>
        // Hiệu ứng xuất hiện từ từ cho các phần tử
        document.addEventListener('DOMContentLoaded', function() {
            const elements = document.querySelectorAll('.welcome-title, .welcome-subtitle, .user-info, .stats, .features, .action-buttons');
            
            elements.forEach((element, index) => {
                setTimeout(() => {
                    element.style.opacity = '0';
                    element.style.transform = 'translateY(20px)';
                    element.style.transition = 'opacity 0.8s, transform 0.8s';
                    
                    setTimeout(() => {
                        element.style.opacity = '1';
                        element.style.transform = 'translateY(0)';
                    }, 100);
                }, index * 200);
            });
            
            // Hiệu ứng hover cho các card tính năng
            const featureCards = document.querySelectorAll('.feature-card');
            featureCards.forEach(card => {
                card.addEventListener('mouseenter', function() {
                    this.style.transform = 'translateY(-10px) scale(1.02)';
                });
                
                card.addEventListener('mouseleave', function() {
                    this.style.transform = 'translateY(0) scale(1)';
                });
            });
        });
    </script>
</body>
</html>
