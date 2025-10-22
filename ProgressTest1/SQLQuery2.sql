CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    UserName NVARCHAR(50) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Role NVARCHAR(20) CHECK (Role IN ('admin', 'user'))
);

INSERT INTO Users (UserID, UserName, Password, Role)
VALUES
(10, 'sa', '123456', 'admin'),
(11, 'admin', 'abc@123', 'admin'),
(12, 'sa1', '123', 'admin'),
(13, N'An Bình', '111', 'user'),
(14, N'An', '222', 'user'),
(15, N'Bình An', '333', 'user');

CREATE TABLE Product (
    id INT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    price DECIMAL(10,2) CHECK (price >= 0),
    description NVARCHAR(255),
    stock INT CHECK (stock >= 0),
    import_date DATETIME
);
select * from Users
INSERT INTO Product (id, name, price, description, stock, import_date)
VALUES
(1, N'Laptop Dell XPS 13', 1500.00, N'Máy tính xách tay cao cấp', 10, '2025-02-10 19:00:00'),
(2, N'Laptop MacBook Pro 14', 2200.00, N'Máy tính xách tay Apple', 15, '2025-02-10 19:00:00'),
(3, N'Smartphone iPhone 14', 999.99, N'Điện thoại Apple', 20, '2025-03-10 19:00:00'),
(4, N'Smartphone Samsung Galaxy S24', 899.99, N'Điện thoại Samsung', 25, '2025-05-10 19:00:00'),
(5, N'Tai nghe Sony WH-1000XM5', 349.99, N'Tai nghe chống ồn', 30, '2025-01-10 19:00:00'),
(6, N'Tai nghe AirPods Pro 2', 249.99, N'Tai nghe không dây Apple', 18, '2025-01-10 19:00:00'),
(7, N'Màn hình LG UltraFine 5K', 1299.99, N'Màn hình 5K dành cho Mac', 8, '2025-02-10 19:00:00');