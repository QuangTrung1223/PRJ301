-- Tạo database
CREATE DATABASE Member;



-- Tạo bảng Member
CREATE TABLE Member (
    MemberID INT IDENTITY(1,1) PRIMARY KEY, -- tự tăng (dùng AUTO_INCREMENT nếu là MySQL)
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(100) NOT NULL,
    Status BIT DEFAULT 1,  -- 1: Active, 0: Inactive
    RoleID INT NOT NULL
);




