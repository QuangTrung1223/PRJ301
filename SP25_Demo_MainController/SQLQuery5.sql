USE Sp25_DemoPRJ;
GO

-- Drop table if exists (optional)
IF OBJECT_ID('Users', 'U') IS NOT NULL
    DROP TABLE Users;
GO

CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(120) NOT NULL,
    email NVARCHAR(220) NOT NULL,
    country NVARCHAR(120) NULL,
    role NVARCHAR(50) NOT NULL,
    status BIT NOT NULL,
    [password] NVARCHAR(255) NOT NULL,
    dob DATE NULL
);
GO

INSERT INTO Users (username, email, country, role, status, [password], dob) VALUES
(N'Chi Pheo', 'chi@gmail.com', N'Viet Nam', N'User', 1, 'abc@123', '1990-05-10'),
(N'Tu Hai', 'hai@fpt.edu.vn', N'Canada', N'User', 1, 'abc@123', '1995-08-21'),
(N'John Doe', 'john.doe@example.com', N'USA', N'Admin', 1, 'abc@123', '1988-02-15'),
(N'Jane Smith', 'jane.smith@example.com', N'UK', N'User', 1, 'abc@123', '1992-07-03'),
(N'Mike Brown', 'mike.brown@example.com', NULL, N'Moderator', 0, 'abc@123', '1985-12-19'),
(N'Sarah Johnson', 'sarah.johnson@example.com', N'Canada', N'User', 1, 'abc@123', '1999-09-09'),
(N'Emily Davis', 'emily.davis@example.com', N'Australia', N'Admin', 0, 'abc@123', '1991-11-25');
GO
