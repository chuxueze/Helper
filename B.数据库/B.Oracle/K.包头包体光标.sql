--在out参数中使用光标
包头：
声明方法
--声明一个存储过程
create or replace package mypackage as 
	--cursor光标
	type empcursor is ref cursor;
	procedure queryEmpList(dno in number,empList out empcursor);
end mypackage;

包体：
包体需要实现包头中声明的所有方法
create or replace package body mypackage as
	procedure queryEmpList(dno in number,empList out empcursor) as
	begin
		--打开光标
		open empList for select * from emp where deptno = dno;
	end queryEmpList;
end mypackage;

--
如需要访问包中的存储过程，则需要带上包名