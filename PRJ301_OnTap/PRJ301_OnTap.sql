USE [PRJ301_OnTap]
GO

UPDATE [dbo].[Student]
   SET [FirstName] = ?
      ,[LastName] = ?
      ,[DateOfBirth] = ?
      ,[Gender] = ?
 WHERE <Search Conditions,,>
GO


