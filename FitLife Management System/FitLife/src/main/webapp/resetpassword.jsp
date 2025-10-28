<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Khôi phục mật khẩu | FitLife</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <section class="auth-section">
        <div class="auth-box">
            <h2>Khôi phục mật khẩu</h2>
            <p class="auth-subtitle">Nhập email để nhận mã OTP</p>
            
            <!-- Form nhập email -->
            <form action="resetpassword" method="post" id="resetForm">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" placeholder="Nhập email của bạn" required>

                <button type="submit" class="btn full-btn">Gửi mã OTP</button>
            </form>

            <!-- Form nhập mã OTP -->
            <c:if test="${not empty sessionScope.otpSent}">
                <form action="verifyotp" method="post" id="otpForm">
                    <label for="otp">Mã OTP</label>
                    <input id="otp" name="otp" type="text" placeholder="Nhập mã OTP 6 chữ số" required>
                    <input type="hidden" name="email" value="${sessionScope.email}">
                    <button type="submit" class="btn full-btn">Xác minh OTP</button>
                </form>
            </c:if>

            <!-- Form nhập mật khẩu mới -->
            <c:if test="${not empty sessionScope.otpVerified}">
                <form action="verifyotp" method="post" id="newPasswordForm">
                    <label for="newPassword">Mật khẩu mới</label>
                    <input id="newPassword" name="newPassword" type="password" placeholder="Nhập mật khẩu mới" required>
                    <label for="confirmPassword">Xác nhận mật khẩu</label>
                    <input id="confirmPassword" name="confirmPassword" type="password" placeholder="Xác nhận mật khẩu" required>
                    <input type="hidden" name="email" value="${sessionScope.email}">
                    <input type="hidden" name="action" value="setNewPassword">
                    <button type="submit" class="btn full-btn">Cập nhật mật khẩu</button>
                </form>
            </c:if>

            <!-- Hiển thị thông báo -->
            <c:if test="${not empty error}">
                <p style="color:red; text-align:center;">${error}</p>
            </c:if>
            <c:if test="${not empty message}">
                <p style="color:green; text-align:center;">${message}</p>
            </c:if>

            <p class="auth-footer">
                Quay lại? 
                <a href="login.jsp" class="link-orange">Đăng nhập</a>
            </p>
        </div>
    </section>
</body>
</html>