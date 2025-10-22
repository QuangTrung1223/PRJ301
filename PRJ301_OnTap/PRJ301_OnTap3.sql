create database PRJ301_OnTap

-- 1. Xóa bảng nếu đã tồn tại
IF OBJECT_ID('dbo.Student', 'U') IS NOT NULL
    DROP TABLE dbo.Student;
GO

-- 2. Tạo bảng Student với 5 cột
CREATE TABLE dbo.Student (
    StudentID INT IDENTITY(1,1) PRIMARY KEY,
    FirstName NVARCHAR(50) NOT NULL,
    LastName NVARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender NVARCHAR(10)
);
GO

-- 3. Chèn dữ liệu mẫu
INSERT INTO dbo.Student (FirstName, LastName, DateOfBirth, Gender)
VALUES
('Nguyen', 'An', '2005-03-15', 'Male'),
('Tran', 'Binh', '2005-07-20', 'Male'),
('Le', 'Chi', '2005-01-10', 'Female'),
('Pham', 'Dung', '2005-11-05', 'Male'),
('Hoang', 'Em', '2005-05-30', 'Female');
GO

-- 4. Kiểm tra dữ liệu
SELECT * FROM dbo.Student;

--CRUD: Create - Read - Update - Delete
