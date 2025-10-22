-- Setup demo users for the e-commerce system
-- Run this script to create demo users in your database

USE Sp25_DemoPRJ;

-- Insert demo users if they don't exist (let IDENTITY handle the ID)
IF NOT EXISTS (SELECT * FROM Users WHERE email = 'admin@gmail.com')
BEGIN
    INSERT INTO Users (username, email, country, role, status, password, dob)
    VALUES ('Admin', 'admin@gmail.com', 'VN', 'Admin', 1, '123456', '1990-01-01');
END

IF NOT EXISTS (SELECT * FROM Users WHERE email = 'user@gmail.com')
BEGIN
    INSERT INTO Users (username, email, country, role, status, password, dob)
    VALUES ('User', 'user@gmail.com', 'VN', 'User', 1, '123456', '1995-05-15');
END

-- Display the users
SELECT * FROM Users;
