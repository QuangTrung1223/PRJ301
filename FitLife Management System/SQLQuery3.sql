-- =========================================
-- DATABASE CREATION
-- =========================================
CREATE DATABASE FitnessDB;
GO
USE FitnessDB;
GO

-- =========================================
-- TABLE: Users
-- =========================================
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    gender NVARCHAR(10) CHECK (gender IN ('Male', 'Female')),
    age INT CHECK (age > 0),
    height FLOAT CHECK (height > 0),
    weight FLOAT CHECK (weight > 0),
    role NVARCHAR(20) DEFAULT 'user', -- 'user' or 'admin'
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- =========================================
-- TABLE: Workouts
-- =========================================
CREATE TABLE Workouts (
    workout_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    workout_name NVARCHAR(100) NOT NULL,
    duration INT CHECK (duration > 0),  -- minutes
    calories_burned INT CHECK (calories_burned >= 0),
    workout_type NVARCHAR(50),
    description NVARCHAR(500),
    bmi_category NVARCHAR(20),
    date DATE DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);
GO

-- =========================================
-- TABLE: Diets
-- =========================================
CREATE TABLE Diets (
    diet_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    meal_name NVARCHAR(100) NOT NULL,
    calories INT CHECK (calories >= 0),
    meal_type NVARCHAR(20),
    description NVARCHAR(500),
    bmi_category NVARCHAR(20),
    date DATE DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);
GO

-- =========================================
-- TABLE: Progress
-- =========================================
CREATE TABLE Progress (
    progress_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    weight FLOAT CHECK (weight > 0),
    muscle_mass FLOAT CHECK (muscle_mass >= 0),
    fat_percent FLOAT CHECK (fat_percent >= 0 AND fat_percent <= 100),
    bmi FLOAT CHECK (bmi > 0),
    bmi_category NVARCHAR(20),
    notes NVARCHAR(500),
    date DATE DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);
GO

-- =========================================
-- TABLE: Admin (optional)
-- =========================================
CREATE TABLE Admin (
    admin_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(100),
    role NVARCHAR(20) DEFAULT 'admin',
    created_at DATETIME DEFAULT GETDATE()
);
GO

select * from 

select * from Courses
CREATE TABLE Courses (
    course_id INT IDENTITY(1,1) PRIMARY KEY,
    course_name NVARCHAR(100) NOT NULL,
    description NVARCHAR(MAX),
    course_type NVARCHAR(50) NOT NULL,
    suitable_bmi_category NVARCHAR(50),
    duration INT NOT NULL,
    calories_burned INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);

-- =========================================
-- SAMPLE DATA
-- =========================================
INSERT INTO Users (username, password, email, gender, age, height, weight, role)
VALUES 
('johnfit', '12345', 'john@example.com', 'Male', 25, 175, 70, 'user'),
('adminfit', 'admin123', 'admin@fitlife.com', 'Male', 30, 180, 75, 'admin'),
('mike_gym', '12345', 'mike@example.com', 'Male', 28, 178, 72, 'user'),
('sara_health', 'fit2025', 'sara@example.com', 'Female', 27, 162, 52, 'user'),
('tony_strong', 'tony123', 'tony@example.com', 'Male', 30, 180, 78, 'user'),
('lisa_workout', 'lisa789', 'lisa@example.com', 'Female', 22, 160, 50, 'user'),
('david_muscle', 'david456', 'david@example.com', 'Male', 29, 182, 80, 'user'),
('jenny_fit', 'jenny321', 'jenny@example.com', 'Female', 26, 158, 48, 'user'),
('kevin_cardio', 'kevin789', 'kevin@example.com', 'Male', 25, 175, 68, 'user'),
('olivia_yoga', 'olivia567', 'olivia@example.com', 'Female', 23, 167, 54, 'user'),
('bruce_power', 'bruce123', 'bruce@example.com', 'Male', 31, 185, 82, 'user'),
('nina_gym', 'nina888', 'nina@example.com', 'Female', 21, 155, 47, 'user'),
('james_run', 'runfast', 'james@example.com', 'Male', 27, 178, 70, 'user'),
('sophia_zen', 'zenlife', 'sophia@example.com', 'Female', 29, 170, 58, 'user'),
('ryan_push', 'pushit', 'ryan@example.com', 'Male', 24, 176, 69, 'user'),
('chloe_balance', 'chloe999', 'chloe@example.com', 'Female', 28, 164, 53, 'user'),
('alex_box', 'boxer123', 'alex@example.com', 'Male', 26, 182, 76, 'user'),
('mia_dance', 'dancefit', 'mia@example.com', 'Female', 25, 160, 51, 'user'),
('leo_core', 'corefit', 'leo@example.com', 'Male', 30, 177, 73, 'user');

GO

INSERT INTO Workouts (user_id, workout_name, duration, calories_burned, workout_type, description, bmi_category, date)
VALUES 
(1, 'Push Ups', 30, 200, 'Strength', 'Upper body strength training', 'normal', '2025-10-01'),
(1, 'Running', 45, 400, 'Cardio', 'Morning jog in the park', 'normal', '2025-10-02'),
(1, 'Squats', 40, 300, 'Strength', 'Leg and glute workout', 'normal', '2025-10-03'),
(1, 'Cycling', 60, 500, 'Cardio', 'Outdoor cycling session', 'normal', '2025-10-04'),
(1, 'Plank', 20, 150, 'Core', 'Abdominal endurance training', 'normal', '2025-10-05'),
(1, 'Yoga', 50, 250, 'Flexibility', 'Morning yoga session', 'normal', '2025-10-06'),
(1, 'Jump Rope', 25, 350, 'Cardio', 'High-intensity jump rope training', 'normal', '2025-10-07'),
(1, 'Bench Press', 40, 280, 'Strength', 'Chest and arm workout', 'normal', '2025-10-08'),
(1, 'Pull Ups', 30, 220, 'Strength', 'Back and biceps training', 'normal', '2025-10-09'),
(1, 'Swimming', 45, 450, 'Cardio', 'Full body pool workout', 'normal', '2025-10-10'),
(1, 'Lunges', 35, 260, 'Strength', 'Leg and balance exercise', 'normal', '2025-10-11'),
(1, 'Burpees', 25, 330, 'HIIT', 'High-intensity interval training', 'normal', '2025-10-12'),
(1, 'Treadmill Run', 40, 370, 'Cardio', 'Indoor treadmill workout', 'normal', '2025-10-13'),
(1, 'Stretching', 30, 120, 'Flexibility', 'Post-workout stretching', 'normal', '2025-10-14'),
(1, 'Deadlift', 35, 310, 'Strength', 'Back and hamstring training', 'normal', '2025-10-15'),
(1, 'Rowing', 50, 480, 'Cardio', 'Rowing machine endurance', 'normal', '2025-10-16');
GO

INSERT INTO Diets (user_id, meal_name, calories, meal_type, description, bmi_category, date)
VALUES 
(1, 'Breakfast - Oatmeal', 350, 'Breakfast', 'Oatmeal with fruits', 'normal', '2025-10-01'),
(1, 'Lunch - Chicken Salad', 450, 'Lunch', 'Grilled chicken with mixed greens', 'normal', '2025-10-01'),
(1, 'Dinner - Salmon Rice', 500, 'Dinner', 'Grilled salmon with rice and broccoli', 'normal', '2025-10-01'),
(1, 'Snack - Yogurt', 180, 'Snack', 'Low-fat yogurt', 'normal', '2025-10-02'),
(1, 'Breakfast - Eggs & Toast', 400, 'Breakfast', 'Scrambled eggs with whole wheat toast', 'normal', '2025-10-02'),
(1, 'Lunch - Beef Bowl', 550, 'Lunch', 'Rice with beef and vegetables', 'normal', '2025-10-03'),
(1, 'Dinner - Chicken Soup', 420, 'Dinner', 'Chicken soup with vegetables', 'normal', '2025-10-03'),
(1, 'Snack - Apple', 100, 'Snack', 'Fresh apple', 'normal', '2025-10-04'),
(1, 'Breakfast - Smoothie', 320, 'Breakfast', 'Banana and milk smoothie', 'normal', '2025-10-05'),
(1, 'Lunch - Tuna Sandwich', 480, 'Lunch', 'Whole wheat bread with tuna and lettuce', 'normal', '2025-10-06'),
(1, 'Dinner - Spaghetti', 530, 'Dinner', 'Spaghetti with tomato sauce', 'normal', '2025-10-07'),
(1, 'Snack - Nuts', 220, 'Snack', 'Mixed nuts', 'normal', '2025-10-08'),
(1, 'Breakfast - Pancakes', 380, 'Breakfast', 'Banana pancakes with honey', 'normal', '2025-10-09'),
(1, 'Lunch - Sushi', 450, 'Lunch', 'Salmon and avocado sushi rolls', 'normal', '2025-10-10'),
(1, 'Dinner - Stir Fry', 510, 'Dinner', 'Chicken stir fry with vegetables', 'normal', '2025-10-11'),
(1, 'Snack - Protein Bar', 200, 'Snack', 'Chocolate protein bar', 'normal', '2025-10-12');
GO

INSERT INTO Progress (user_id, weight, muscle_mass, fat_percent, bmi, bmi_category, notes, date)
VALUES 
(1, 69.5, 29.0, 15.5, 22.7, 'normal', 'Good progress this week', '2025-10-01'),
(1, 69.3, 29.2, 15.4, 22.6, 'normal', 'Slight fat reduction', '2025-10-02'),
(1, 69.0, 29.3, 15.3, 22.5, 'normal', 'Stable muscle mass', '2025-10-03'),
(1, 68.9, 29.4, 15.2, 22.4, 'normal', 'Improved endurance', '2025-10-04'),
(1, 68.7, 29.5, 15.0, 22.3, 'normal', 'Good workout consistency', '2025-10-05'),
(1, 68.5, 29.7, 14.8, 22.2, 'normal', 'Better muscle definition', '2025-10-06'),
(1, 68.4, 29.8, 14.7, 22.1, 'normal', 'Increasing muscle tone', '2025-10-07'),
(1, 68.2, 30.0, 14.6, 22.0, 'normal', 'Great overall condition', '2025-10-08'),
(1, 68.0, 30.1, 14.5, 21.9, 'normal', 'Slight improvement in muscle mass', '2025-10-09'),
(1, 67.9, 30.2, 14.4, 21.8, 'normal', 'Maintaining healthy balance', '2025-10-10'),
(1, 67.7, 30.4, 14.3, 21.7, 'normal', 'Excellent diet consistency', '2025-10-11'),
(1, 67.6, 30.5, 14.2, 21.6, 'normal', 'Stable and healthy BMI', '2025-10-12'),
(1, 67.5, 30.6, 14.1, 21.5, 'normal', 'Good progress', '2025-10-13'),
(1, 67.4, 30.7, 14.0, 21.4, 'normal', 'Excellent discipline', '2025-10-14'),
(1, 67.2, 30.8, 13.9, 21.3, 'normal', 'Very consistent workouts', '2025-10-15'),
(1, 67.1, 30.9, 13.8, 21.2, 'normal', 'Great progress', '2025-10-16'),
(1, 67.0, 31.0, 13.7, 21.1, 'normal', 'Noticeable fat loss', '2025-10-17'),
(1, 66.9, 31.1, 13.6, 21.0, 'normal', 'Reaching best form', '2025-10-18');
GO


