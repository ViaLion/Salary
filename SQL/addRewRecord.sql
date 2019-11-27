-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE addRewRecord 
	-- Add the parameters for the stored procedure here
	@RewID varchar(50),@EmpID varchar(50),@RewSalary int,@RewRecord varchar(50)
AS
	if(select count(*) from Reward where RewID = @RewID) = 0
	BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	insert into Reward values(@RewID,@EmpID,@RewSalary,@RewRecord)
	end

	else
	begin
	update Reward
	set EmpID = @EmpID,RewSalary = @RewSalary,RewDescription = @RewRecord 
	where RewID = @RewID
	END
GO
