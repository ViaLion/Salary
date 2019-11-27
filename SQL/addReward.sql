-- ================================================
-- Template generated from Template Explorer using:
-- Create Scalar Function (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the function.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date, ,>
-- Description:	<Description, ,>
-- =============================================
CREATE FUNCTION addRews(@EmpID varchar) 
RETURNS int
AS
BEGIN
	-- Declare the return variable here
	DECLARE @Rewards int

	-- Add the T-SQL statements to compute the return value here
	SELECT @Rewards = @Rewards + RTRIM(Reward.RewSalary) 
										from Reward where Reward.EmpID = @EmpID 

	-- Return the result of the function
	RETURN @Rewards

END
GO

