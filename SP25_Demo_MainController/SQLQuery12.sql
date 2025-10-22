
CREATE TABLE Orders (
    id INT PRIMARY KEY IDENTITY(1,1),                  -- ID đơn hàng, tự động tăng
    user_id INT NOT NULL,                              -- Người đặt hàng (liên kết với Users)
    order_date DATETIME DEFAULT GETDATE(),             -- Ngày đặt hàng, mặc định là thời gian hiện tại
    total_price DECIMAL(10,2) NOT NULL,                -- Tổng tiền của đơn hàng
    status NVARCHAR(50) NOT NULL DEFAULT N'Pending',   -- Trạng thái đơn hàng
    FOREIGN KEY (user_id) REFERENCES Users(id)         -- Khóa ngoại tham chiếu Users
);
GO


INSERT INTO Orders (user_id, total_price, status)
VALUES (1, 1500.00, N'Pending');
GO


CREATE TABLE OrderDetails (
    id INT PRIMARY KEY IDENTITY(1,1),                  -- ID chi tiết đơn hàng, tự động tăng
    order_id INT NOT NULL,                             -- Liên kết với bảng Orders
    product_id INT NOT NULL,                           -- Liên kết với bảng Product
    quantity INT NOT NULL CHECK (quantity > 0),        -- Số lượng sản phẩm
    price DECIMAL(10,2) NOT NULL,                      -- Giá tại thời điểm mua
    subtotal AS (quantity * price) PERSISTED,          -- Tổng tiền từng sản phẩm (computed column)
    FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE, -- Khi xóa đơn hàng thì xóa chi tiết
    FOREIGN KEY (product_id) REFERENCES Product(id)    -- Liên kết với Product
);
GO
select * from OrderDetails
select * from Orders


USE Sp25_DemoPRJ;
INSERT INTO Users (username, email, country, role, status, password, dob)
VALUES ('Admin', 'admin@gmail.com', 'VN', 'Admin', 1, '123456', '1990-01-01');