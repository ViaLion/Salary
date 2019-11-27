Create Database SalaryManagement;
Use SalaryManagement;

/*管理员表*/
CREATE TABLE Admins (
	AdminId INT identity(1,1) primary key NOT NULL,
	AdminName VARCHAR(40) unique not null,
	pswd VARCHAR(40) not null
	);
select * from Admins;

/*学院表*/
CREATE TABLE Department (
	DepId INT identity(1,1) primary key NOT NULL,
	DepName VARCHAR(40),
	DepTel VARCHAR(15),
	counter INT DEFAULT 0
	);
select * from Department;

/*职业类别*/
CREATE TABLE Branch (
	BName VARCHAR(50) NOT NULL,
	BID INT identity(1,1) primary key NOT NULL,
	BasicSalary INT NOT NULL,
	counter INT DEFAULT 0
	);
select * from Branch;

/*教职工表*/
CREATE TABLE Employee (
	EmpID INT primary key, 
	EmpName VARCHAR(40) NOT NULL,
	pswd VARCHAR(40) NOT NULL,
	EmpSex CHAR NOT NULL,
	DepId INT NOT NULL,
	BID INT NOT NULL,
	EmpTel VARCHAR(20) NOT NULL,
	EmpDescription TEXT,
	foreign key (DepId) references Department(DepId) on delete no action on update cascade,
	foreign key (BID) references Branch(BID) on delete no action on update cascade
	);
select * from Employee;

/*旷工记录*/
CREATE TABLE Record(
	RedID INT identity(1,1) primary key,
	EmpID INT,
	record VARCHAR(50) NOT NULL,
	ReDescription TEXT,
	counter int DEFAULT 0,
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Record;

/*奖励表*/
CREATE TABLE Reward(
	RewID INT identity(1,1) primary key,
	EmpID INT,
	RewSalary INT NOT NULL,
	RewDescription TEXT,
	counter int DEFAULT 0,
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Reward;

/*处罚表*/
CREATE TABLE Punishment(
	PID INT identity(1,1) primary key,
	EmpID INT,
	PunSalary INT NOT NULL,
	PunDescription TEXT,
	counter int DEFAULT 0
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Punishment;



/*触发器 插入一个教职工，对应教职工总数加一*/
create trigger EmpInsert  
on  Employee  
for Insert as    
declare     
 @DepId int  
Begin   
    select @DepId = DepId   
        from inserted  
    update Department     
    set counter = counter + 1    
    where DepId = @DepId    
End   
/*触发器 删除一个教职工，对应教职工总数减一*/
create trigger EmpDelete   
on  Employee   
for delete as    
declare     
 @DepId int  
Begin   
    select @DepId = DepId   
        from deleted  
    update Department     
    set counter = counter - 1    
    where DepId = @DepId    
End

/*视图：工资查询*/
create view ViewSalary with SCHEMABINDING
as
select dbo.Employee.EmpId, dbo.Employee.EmpName,sum(dbo.Punishment.PunSalary) as Punishments,sum(dbo.Reward.RewSalary) as Rewards
from dbo.Employee,dbo.Punishment,dbo.Reward
where dbo.Employee.EmpID = dbo.Punishment.EmpID and dbo.Employee.EmpID = dbo.Reward.EmpID
group by dbo.Employee.EmpID, dbo.Employee.EmpName;
go
select * from ViewSalary;

/*视图：总工资查询*/
create view Salary with SCHEMABINDING
as
select dbo.Employee.EmpId, dbo.Employee.EmpName, dbo.Employee.EmpSex, dbo.Employee.EmpTel, dbo.Department.DepName, 
		dbo.Branch.BName,dbo.Branch.BasicSalary,dbo.ViewSalary.Rewards,dbo.ViewSalary.Punishments,
		(dbo.Branch.BasicSalary + dbo.ViewSalary.Rewards - dbo.ViewSalary.Punishments) as TotalSalary
		from dbo.Employee,dbo.Branch,dbo.ViewSalary,dbo.Department
where dbo.Employee.BID = dbo.Branch.BID and dbo.Employee.EmpID = dbo.ViewSalary.EmpID and 
	  dbo.Employee.BID = dbo.Branch.BID and dbo.Employee.DepId = dbo.Department.DepId and
	  dbo.Employee.EmpID = dbo.ViewSalary.EmpId
group by dbo.Employee.EmpId, dbo.Employee.EmpName,dbo.Branch.BasicSalary ,dbo.ViewSalary.Rewards ,
		 dbo.ViewSalary.Punishments,dbo.Employee.EmpSex,dbo.Employee.EmpTel,dbo.Department.DepName,
		 dbo.Branch.BName,dbo.Branch.BasicSalary,dbo.ViewSalary.Rewards,dbo.ViewSalary.Punishments;
go
select * from Salary;



/*给教职工信息表建立索引“职工”*/
create index Staff on Employee(EmpID)
go
select * 
from sys.indexes
where name = 'Staff'
/*给奖金表建立唯一索引“奖金”*/
create unique index TotalReward on Reward(EmpID)
go
select *
from sys.indexes
where name = 'TotalReward'
go
/*给处罚表建立唯一索引“处罚”*/
create unique index TotalPunishment on Punishment(EmpID)
go
select *
from sys.indexes
where name = 'TotalPunishment'
go

Insert into Department values ('1','信息学院','123456789');

Insert into Branch values ('c++','1','5000');
Insert into Branch values ('Java','2','5000');
Insert into Branch values ('数据结构','3','5000');

Insert into Employee values ('1','张三','1','M','3','1','123456','');
Insert into Employee values ('2','李四','2','W','3','2','123789','');
Insert into Employee values ('3','王五','3','W','3','3','123567','');
Insert into Employee values ('4','赵六','4','M','3','3','123678','');