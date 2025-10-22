USE [DB_CafeShop]
GO

INSERT INTO [dbo].[Account]
           ([Username]
           ,[Password])
     VALUES
           (<Username, nvarchar(50),>
           ,<Password, nvarchar(50),>)
GO


ALTER TABLE dbo.Account
ADD Email NVARCHAR(100) NULL,
    Phone NVARCHAR(20) NULL;
	