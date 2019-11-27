Create Database SalaryMS;
use SalaryMS;
 
/*����Ա��*/
CREATE TABLE Admin (
 AdminId INT identity(1,1) primary key NOT NULL,
 AdminName VARCHAR(40) unique not null,
 pswd VARCHAR(40) not null
);
 
/*���ű�*/
CREATE TABLE Department (
 DepId INT identity(1,1) primary key NOT NULL,
 DepName VARCHAR(40),
 DepTel VARCHAR(15),
 counter int DEFAULT 0
);
/*�������*/
CREATE TABLE Item (
 ItemId INT identity(1,1) primary key,
 ItemName VARCHAR(40) unique NOT NULL,
 ItemType int DEFAULT 1 NOT NULL,
 counter int DEFAULT 0
);
/*Ա����*/
CREATE TABLE Employee (
 EmpId varchar(18) primary key,
 EmpName VARCHAR(40) not null,
 pswd VARCHAR(40) not null,
 DepId INT not null,
 foreign key (DepId) references Department(DepId) on delete no action on update cascade
);
/*���ʱ�*/
CREATE TABLE Salary (
 EmpId varchar(18) NOT NULL,
 ItemId INT NOT NULL,
 ItemSalary numeric(18,2),
 SDate varchar(18) NOT NULL,
 primary key(empId,itemId,SDate),
 foreign key (ItemId) references Item(ItemId) on delete no action on update cascade,
 foreign key (EmpId) references Employee(EmpId) on delete no action on update cascade
);
 
 
 
 
/*������ ����һ��ʣ���Ӧ������������һ*/
create trigger SalaryInsert  
on  Salary  
for Insert as    
declare     
 @ItemId int  
Begin   
    select @ItemId = ItemId   
        from inserted  
    update Item     
    set counter = counter + 1    
    where ItemId = @ItemId    
End   
/*������ ɾ��һ��ʣ���Ӧ������������һ*/
 
 
create trigger SalaryDelete  
on  Salary  
for delete as    
declare     
 @ItemId int  
Begin   
    select @ItemId = ItemId   
        from deleted  
    update Item     
    set counter = counter - 1    
    where ItemId = @ItemId    
End  
 
 
/*������ ����һ��Ա������ӦԱ��������һ*/
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
/*������ ɾ��һ��Ա������ӦԱ��������һ*/
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
create view  v_salary
as
select Salary.SDate,Item.ItemName,Employee.EmpId, Employee.EmpName,Item.ItemType*Salary.ItemSalary as Salary
from Salary,Employee,Item
where Salary.EmpId = Employee.EmpId and Salary.ItemId = Item.ItemId ;
 
 
/*��ͼ���ܹ��ʲ�ѯ*/
create view  v_TotalSalary
as
select Salary.SDate,Employee.EmpId, Employee.EmpName,sum(Item.ItemType*Salary.ItemSalary) as  totalSalary
from Salary,Employee,Item
where Salary.EmpId = Employee.EmpId and Salary.ItemId = Item.ItemId 
group by Salary.SDate,Employee.EmpId,Employee.EmpName;
 
 