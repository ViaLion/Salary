Create Database SalaryManagement;
Use SalaryManagement;

/*����Ա��*/
CREATE TABLE Admins (
	AdminId INT identity(1,1) primary key NOT NULL,
	AdminName VARCHAR(40) unique not null,
	pswd VARCHAR(40) not null
	);
select * from Admins;

/*ѧԺ��*/
CREATE TABLE Department (
	DepId INT identity(1,1) primary key NOT NULL,
	DepName VARCHAR(40),
	DepTel VARCHAR(15),
	counter INT DEFAULT 0
	);
select * from Department;

/*ְҵ���*/
CREATE TABLE Branch (
	BName VARCHAR(50) NOT NULL,
	BID INT identity(1,1) primary key NOT NULL,
	BasicSalary INT NOT NULL,
	counter INT DEFAULT 0
	);
select * from Branch;

/*��ְ����*/
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

/*������¼*/
CREATE TABLE Record(
	RedID INT identity(1,1) primary key,
	EmpID INT,
	record VARCHAR(50) NOT NULL,
	ReDescription TEXT,
	counter int DEFAULT 0,
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Record;

/*������*/
CREATE TABLE Reward(
	RewID INT identity(1,1) primary key,
	EmpID INT,
	RewSalary INT NOT NULL,
	RewDescription TEXT,
	counter int DEFAULT 0,
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Reward;

/*������*/
CREATE TABLE Punishment(
	PID INT identity(1,1) primary key,
	EmpID INT,
	PunSalary INT NOT NULL,
	PunDescription TEXT,
	counter int DEFAULT 0
	foreign key (EmpID) references Employee(EmpID) on delete no action on update cascade
	);
select * from Punishment;



/*������ ����һ����ְ������Ӧ��ְ��������һ*/
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
/*������ ɾ��һ����ְ������Ӧ��ְ��������һ*/
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

/*��ͼ�����ʲ�ѯ*/
create view ViewSalary with SCHEMABINDING
as
select dbo.Employee.EmpId, dbo.Employee.EmpName,sum(dbo.Punishment.PunSalary) as Punishments,sum(dbo.Reward.RewSalary) as Rewards
from dbo.Employee,dbo.Punishment,dbo.Reward
where dbo.Employee.EmpID = dbo.Punishment.EmpID and dbo.Employee.EmpID = dbo.Reward.EmpID
group by dbo.Employee.EmpID, dbo.Employee.EmpName;
go
select * from ViewSalary;

/*��ͼ���ܹ��ʲ�ѯ*/
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



/*����ְ����Ϣ����������ְ����*/
create index Staff on Employee(EmpID)
go
select * 
from sys.indexes
where name = 'Staff'
/*���������Ψһ����������*/
create unique index TotalReward on Reward(EmpID)
go
select *
from sys.indexes
where name = 'TotalReward'
go
/*����������Ψһ������������*/
create unique index TotalPunishment on Punishment(EmpID)
go
select *
from sys.indexes
where name = 'TotalPunishment'
go

Insert into Department values ('1','��ϢѧԺ','123456789');

Insert into Branch values ('c++','1','5000');
Insert into Branch values ('Java','2','5000');
Insert into Branch values ('���ݽṹ','3','5000');

Insert into Employee values ('1','����','1','M','3','1','123456','');
Insert into Employee values ('2','����','2','W','3','2','123789','');
Insert into Employee values ('3','����','3','W','3','3','123567','');
Insert into Employee values ('4','����','4','M','3','3','123678','');