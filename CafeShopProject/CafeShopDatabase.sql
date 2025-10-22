Create Database DB_CafeShop


CREATE TABLE Account (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL,
    [Password] NVARCHAR(200) NOT NULL,

);

ALTER TABLE Account DROP COLUMN email;
ALTER TABLE Account ADD email NVARCHAR(100);

ALTER TABLE Account DROP COLUMN phone;
ALTER TABLE Account ADD phone NVARCHAR(100);


select * from Account




INSERT INTO Account (Username, [Password])
VALUES
(N'admin', N'123456'),
(N'trung', N'abc123'),
(N'john', N'john2025'),
(N'mary', N'm@rypass'),
(N'alex', N'alex789');

select * from Account
EXEC sp_columns Account;

select * from Account
select * from Role
select * from AccountRole
-- 1️⃣ Đổi tên bảng gốc để giữ dữ liệu
EXEC sp_rename 'AccountRole', 'AccountRole_Base';

-- 2️⃣ Tạo VIEW trùng tên với bảng cũ
CREATE VIEW AccountRole AS
SELECT 
    a.Username AS Username,
    ar.RoleId
FROM AccountRole_Base ar
JOIN Account a ON ar.AccountId = a.UserId;

;












