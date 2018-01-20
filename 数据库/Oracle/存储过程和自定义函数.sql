概念：
存储过程和存储函数
指存储在数据库中供所有用户程序调用的子程序叫存储过程，存储函数。
特点：完成特定功能的程序。
区别：存储函数可以用return语句返回值。存储过程不可以用return返回值。
注意：
一般不在存储函数和存储过程中做提交或回滚操作。因为最好将提交或回滚提供给调用者

--创建存储过程
create or replace procedure pro_sayHelloWorld
	as
begin
  dbms_output.put_line('hellow World');
end;  
--两种调用存储过程的方法
1：exec pro_sayHelloWorld();
2.begin 
	pro_sayHelloWorld();
	pro_sayHelloWorld();
  end

--带参数的存储过程
--给指定员工涨100块钱工资，并且打出涨前和涨后的薪水  
create or replace procedure pro_salary(name in varchar2)
	as
begin
	update user set salary=salary+100 where username=name 
end;


--存储函数  
--查询某个员工的年收入
create or replace function fun_queryUser(name in varchar2)
return number
	as
	--定义变量保存员工的薪水和奖金
	psal user.salary%type;
	pcomm user.comm%type;
begin
	--得到员工的月薪和奖金
	select salary,comm into psal,pcomm from user where username=name;
	--直接返回年收入
	return psal*12+nvl(pcomm,0 );
end;

--使用场景
--原则： 
--如果只有一个返回值，则用存储函数。否则，就用存储过程

--out
--查询某个员工姓名 月薪 职位
create or replace procedure pro_queryForm(
	eno in number,
	pename out varchar2,
	pasl out number,
	pjob out varchar2
)
	as
begin
	select ename,sal,empjob into pename,psal,pjob from emp where empno=eno;
end;