<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán Khóa Học</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #d3d3d3;
            color: #333;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: flex-start;
        }
        .container {
            display: flex;
            width: 100%;
            max-width: 1200px;
            margin: 20px 0;
            padding: 0 20px;
            gap: 20px;
        }
        .order-info, .order-summary {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            flex: 1;
        }
        .order-info h2, .order-summary h2 {
            color: #ff6200;
            text-align: left;
            margin-bottom: 20px;
            font-size: 24px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #333;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 2px solid #ff6200;
            border-radius: 4px;
            background-color: #f5f5f5;
            color: #333;
            box-sizing: border-box;
            font-size: 16px;
        }
        input:focus {
            outline: none;
            border-color: #e65100;
            background-color: #ffffff;
        }
        .order-summary-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            color: #333;
            word-wrap: break-word;
        }
        .order-summary-item .price {
            color: #28a745;
        }
        .order-summary-total {
            display: flex;
            justify-content: space-between;
            font-weight: bold;
            margin-top: 20px;
            border-top: 1px solid #ccc;
            padding-top: 10px;
        }
        button {
            background-color: #ff6200;
            color: #ffffff;
            padding: 12px 25px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
            width: 100%;
        }
        button:hover {
            background-color: #e65100;
        }
        .back-btn {
            background-color: #808080;
            margin-right: 10px;
        }
        .back-btn:hover {
            background-color: #666;
        }
        .button-group {
            display: flex;
            gap: 10px;
        }
        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
            z-index: 1000;
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }
        .modal.active {
            display: flex;
            opacity: 1;
        }
        .modal-content {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            max-width: 400px;
            width: 90%;
            transform: scale(0.9);
            transition: transform 0.3s ease-in-out;
        }
        .modal-content.active {
            transform: scale(1);
        }
        .modal-content h3 {
            color: #ff6200;
            margin-bottom: 15px;
        }
        .modal-content p {
            color: #333;
            margin-bottom: 20px;
        }
        .modal-close {
            background-color: #ff6200;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .modal-close:hover {
            background-color: #e65100;
        }
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                padding: 0 10px;
            }
            .order-info, .order-summary {
                width: 100%;
            }
            .button-group {
                flex-direction: column;
            }
            .back-btn, button {
                width: 100%;
            }
            .modal-content {
                width: 80%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="order-info">
            <h2>Thông Tin Khách Hàng</h2>
            <label for="fullName">Họ và Tên:</label>
            <input type="text" id="fullName" name="fullName" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Số Điện Thoại:</label>
            <input type="tel" id="phone" name="phone" pattern="[0-9]{10,11}" placeholder="Ví dụ: 0912345678" required>

            <label for="shippingAddress">Địa Chỉ:</label>
            <input type="text" id="shippingAddress" name="shippingAddress" required>

            <label for="paymentMethod">Phương Thức Thanh Toán:</label>
            <select id="paymentMethod" name="paymentMethod" required>
                <option value="">Chọn phương thức thanh toán</option>
                <option value="credit">Thẻ tín dụng</option>
                <option value="cod">Tiền Mặt</option>
            </select>
        </div>
        <div class="order-summary">
            <h2>Tóm Tắt Khóa Học</h2>
            <div class="order-summary-item">
                <span>Khóa Học: Fitness For Newbie</span>
                <span class="price">1,500,000 VND</span>
            </div>
            <div class="order-summary-total">
                <span>Tổng cộng:</span>
                <span class="price">1,500,000 VND</span>
            </div>
            <div class="button-group">
                <button class="back-btn" onclick="window.history.back()">Quay Lại Trang Chủ</button>
                <button type="button" onclick="processPayment()">Thanh Toán</button>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="successModal" class="modal">
        <div class="modal-content">
            <h3>Thành Công!</h3>
            <p>Thanh toán thành công! Số tiền: 1,500,000 VND.</p>
            <button class="modal-close" onclick="redirectToDashboard()">Đóng</button>
        </div>
    </div>

    <script>
        function processPayment() {
            const fullName = document.getElementById('fullName').value;
            const email = document.getElementById('email').value;
            const phone = document.getElementById('phone').value;
            const shippingAddress = document.getElementById('shippingAddress').value;
            const paymentMethod = document.getElementById('paymentMethod').value;

            if (!fullName || !email || !phone || !shippingAddress || !paymentMethod) {
                alert("Vui lòng điền đầy đủ thông tin.");
                return;
            }
            if (!/^[0-9]{10,11}$/.test(phone)) {
                alert("Số điện thoại không hợp lệ. Vui lòng nhập 10 hoặc 11 số.");
                return;
            }

            // Hiển thị modal với animation
            const modal = document.getElementById('successModal');
            const modalContent = document.querySelector('.modal-content');
            modal.classList.add('active');
            modalContent.classList.add('active');
            setTimeout(() => {
                modal.classList.remove('active');
                modalContent.classList.remove('active');
                redirectToDashboard();
            }, 3000); // Tự động đóng sau 3 giây
        }

        function redirectToDashboard() {
            window.location.href = "http://localhost:8080/FitLife/user-dashboard.jsp";
        }
    </script>
</body>
</html>