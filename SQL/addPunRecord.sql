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
CREATE PROCEDURE addPunRecord 
	-- Add the parameters for the stored procedure here
	@PunID varchar(50),@EmpID varchar(50),@PunSalary int,@PunRecord varchar(50)
AS
	if(select count(*) from Punishment where PunID = @PunID) = 0
	BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	insert into Punishment values(@PunID,@EmpID,@PunSalary,@PunRecord)
	END

	else
	begin
	update Punishment
	set PunID = @PunID,EmpID = @EmpID,PunSalary = @PunSalary,PunDescription = @PunRecord
	end
GO
