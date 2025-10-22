CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(120) NOT NULL,
    email NVARCHAR(220) NOT NULL,
    country NVARCHAR(120) NULL,
    role NVARCHAR(50) NOT NULL,
    status BIT NOT NULL,
    password NVARCHAR(255) NOT NULL,
    dob DATE NULL
);

INSERT INTO Users (username, email, country, role, status, password, dob) VALUES
(N'Chi Pheo', 'chi@gmail.com', N'Viet Nam', N'User', 1, 'abc@123', '1990-01-01'),
(N'Tu Hai', 'hai@fpt.edu.vn', N'Canada', N'User', 1, 'abc@123', '1992-05-10'),
(N'John Doe', 'john.doe@example.com', N'USA', N'Admin', 1, 'abc@123', '1985-03-15');

SELECT * FROM Users;
