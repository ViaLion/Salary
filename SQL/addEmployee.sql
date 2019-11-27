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
CREATE PROCEDURE addEmployee 
	-- Add the parameters for the stored procedure here
	@EmpID varchar(50),@EmpName varchar(50),@EmpTel varchar(50),
	@EmpSex varchar(50),@DepID varchar(50),@BID varchar(50),@EmpPassword varchar(50)
AS 
	if(select COUNT(*) from Employee where EmpID = @EmpID) = 0
	BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	insert into Employee values(@EmpID,@EmpName,@EmpTel,@EmpSex,@DepID,@BID,@EmpPassword)
	end

	else
	begin
	update Employee
	set EmpName = @EmpName,EmpTel = @EmpTel,EmpSex = @EmpSex,DepID = @DepID,BID = @BID,EmpPassword = @EmpPassword
	where EmpID = @EmpID
END
GO
