use SalaryManagement;

create index Staff on Employee(EmpID)
go
select *
from sys.indexes
where name = 'Staff'