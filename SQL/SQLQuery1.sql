Create Database SalaryMS;
use SalaryMS;
 
/*管理员表*/
CREATE TABLE Admin (
 AdminId INT identity(1,1) primary key NOT NULL,
 AdminName VARCHAR(40) unique not null,
 pswd VARCHAR(40) not null
);
 
/*部门表*/
CREATE TABLE Department (
 DepId INT identity(1,1) primary key NOT NULL,
 DepName VARCHAR(40),
 DepTel VARCHAR(15),
 counter int DEFAULT 0
);
/*工资项表*/
CREATE TABLE Item (
 ItemId INT identity(1,1) primary key,
 ItemName VARCHAR(40) unique NOT NULL,
 ItemType int DEFAULT 1 NOT NULL,
 counter int DEFAULT 0
);
/*员工表*/
CREATE TABLE Employee (
 EmpId varchar(18) primary key,
 EmpName VARCHAR(40) not null,
 pswd VARCHAR(40) not null,
 DepId INT not null,
 foreign key (DepId) references Department(DepId) on delete no action on update cascade
);
/*工资表*/
CREATE TABLE Salary (
 EmpId varchar(18) NOT NULL,
 ItemId INT NOT NULL,
 ItemSalary numeric(18,2),
 SDate varchar(18) NOT NULL,
 primary key(empId,itemId,SDate),
 foreign key (ItemId) references Item(ItemId) on delete no action on update cascade,
 foreign key (EmpId) references Employee(EmpId) on delete no action on update cascade
);
 
 
 
 
/*触发器 插入一项工资，对应工资项总数加一*/
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
/*触发器 删除一项工资，对应工资项总数减一*/
 
 
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
 
 
/*触发器 插入一个员工，对应员工总数加一*/
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
/*触发器 删除一个员工，对应员工总数减一*/
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
create view  v_salary
as
select Salary.SDate,Item.ItemName,Employee.EmpId, Employee.EmpName,Item.ItemType*Salary.ItemSalary as Salary
from Salary,Employee,Item
where Salary.EmpId = Employee.EmpId and Salary.ItemId = Item.ItemId ;
 
 
/*视图：总工资查询*/
create view  v_TotalSalary
as
select Salary.SDate,Employee.EmpId, Employee.EmpName,sum(Item.ItemType*Salary.ItemSalary) as  totalSalary
from Salary,Employee,Item
where Salary.EmpId = Employee.EmpId and Salary.ItemId = Item.ItemId 
group by Salary.SDate,Employee.EmpId,Employee.EmpName;
 
 