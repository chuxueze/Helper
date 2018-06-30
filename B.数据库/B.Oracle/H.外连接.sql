--分组查询
--分组函数作用于一组数据，并对一组数据返回一个值
--常用分组函数
--avg  sum  min  max  count  wm_concat
wm_concat（行转列）
select deptno 部门号，wm_concat(ename) 部门中员工的姓名 
	from emp groupt by deptno;
结果：
10号部门  张浩纯，黄洁平，蒋大大...
20号部门  罗叶， 刘工，卜总...

--统计员工的平均工资（查找所有员工工资再除以员工人数以求平均数）
--分组函数会自动忽略空值
select avg(sal)  from emp;
--求每个部门的平均工资，显示：部门号，部门的平均工资
select deptno,avg(sal) from emp group by deptno;
--所有select 列表中所有未包含在组函数中的列都应该包含在group by子句中
select a,b,c,avg(sal) from emp group by a,b,c;
--(先按照a分组，如果a相同，再按照b分组，以此类推) 

--过滤分组having 
select depton,avg(sal) from emp group by depton having avg(sal) > 2000;
--查询10号部门的平均工资
--尽量使用where（效率更高）where:先过滤再分组  having:先分组再过滤
select depton,avg(sal) from emp group by depton having depton = '10';
select depton,avg(sal) from emp where depton = '10' group by depton;

--order by 
select depton,avg(sal) from emp group by depton order by avg(sal);

--分组嵌套（求部门平均工资的最大值）
select depton,max(avg(sal)) from emp group by depton;

--group by 语句的增强rollup();
(
select depton,job,sum(sal) from emp group by depton,job;
select depton,job,sum(sal) from emp group by depton;
select depton,job,sum(sal) from emp;
)
--相当于上述三个语句的结果集
select depton,job,sum(sal) from emp group by rollup(depton,job);


--------------------------
--------------------------
多表查询
内连接,外连接，自连接，层次查询，等值连接，不等值连接
--笛卡尔集  如a表有10条数据，b表有10条数据，则关联结果为100条数据
select * from a,b;
--等值连接
select e.empno from emp e,dept d where e.deptno = d.deptno;

--左外连接
--当连接条件不成立的时候，等号左边的表仍然被包含
--(以左表数据为主，保留左边全部数据)

--右外连接
--当连接条件不成立的时候，等号右边的表仍然被包含
--（以右表数据为主，保留右边全部数据）

--外连接
--即" (+)"所在位置的另一侧为连接的方向,通常将全量集合与部分集合连接时，在部分集合的列后面带上（+）
SELECT a.*, b.* from a(+) = b就是一个右连接，
等同于
select a.*, b.* from a right join b

SELECT a.*, b.* from a = b(+)就是一个左连接，
等同于
select a.*, b.* from a left join b

--自连接（不适合操作大表）
--查询老板下面有哪些员工，相同的一个表，不同的别名，当两个表看
select boss_name,emp_name from emp a, emp b where a.boss_no = b.emp_no

--层次查询（解决自连接数据量过多的情况）
--类似于树结构 
select level,empno,ename,sal from emp 
	connect by prior emp_no = boss_no 
		start with boss_no is null
			order by 1;
--start with 指定从树的哪个节点开始遍历
--以上为从根节点开始遍历整棵树
--也可以从指定节点开始遍历子树
select * from emp connect by prior emp_no = boss_no start with emp_no = '1000'
--(从emp_no等于1000开始遍历)



--------------------------------
--------------------------------
-- 子查询
--查询工资比scott高的员工
select emp_name,sal from emp
	where sal > (select sal from emp where emp_name='scott')
--使用子查询的位置（where,select,having,from）

--select后面，必须是一个单行子查询，只返回一条记录 
select empno,ename,sal,(select job from emp where empno = '1010')
	from emp;
--having后面
select deptno,avg(sal) from emp 
	group by deptno 
		having avg(sal) > (select max (sal)
								from emp where deptno = '1010')
--from后面(可以将子查询的结果当成是一张新的表)
select * from (select empno,ename,sal from emp)
--子查询和子查询可以不是同一张表
select * from emp where deptno = (
								select deptno from dept where dname = 'sales'	
										)
--子查询的排序问题
--rownum行号，伪列(默认一个顺序，行号不随排序而改变  )
select rownum,empno,enmae,sal
	from emp 
		where rownum <= 3
			order by sal desc;
--外层行号为，子查询的行号
select rownum,empno,ename,sal from (
	select * from emp order by sal desc
	)where rownum<=3;
--相关子查询
select empno,ename,sal,(select avg(sal) from emp where deptno = e.deptno) from emp e  
	where sal > (select avg(sal) from emp 
			where deptno = e.deptno);	
--单行子查询只能使用单行操作符;多行子查询只能使用多行操作符
--单行子查询，只返回一条记录
--单行操作符
--（=  >  >=  <  <=  <>）
--多行子查询，返回多条记录
--多行操作符
--（in any all）
--(查询员工信息，要求1：职位与1010员工一样，2：薪水大于1020员工的薪水)
select * from emp 
	where job = (select job from emp where empno='1010') and 
		  sal > (select sal from emp where empno='1020')
--查出工资最低的员工
select * from emp 
	where sal = (select min(sal) from emp);
--查询最低工资大于20号部门最低工资的部门号和部门的最低工资
select deptno,min(sal)
	from emp 
		group by deptno
			having min(sal) > (select min(sal) from emp where deptno=20)

--多行操作符
--查询部门名称是sales和accounting的员工信息
--子查询
select * from emp where deptno in(
	select deptno from dept where dep_name ='Sales' or dep_name='accounting';
)
--表关联
select e.*
  from emp e, dept d
 where e.deptno = d.deptno
   and d.dep_name in ('sales','accounting')

--查询工资比30号部门任意其中一个员工高的员工信息
select e.*
  from emp e
 where e.sal > (select min(d.sal) from emp d where d.deptno = 30)

select e.*
  from emp e
 where e.sal > any (select d.sal from emp d where d.deptno = 30)

--查询工资比30号部门所有员工高的员工信息
select e.*
  from emp e
 where e.sal > (select max(d.sal) from emp d where d.deptno = 30)

select e.*
  from emp e
 where e.sal > all (select d.sal from emp d where d.deptno = 30)


--子查询中的null值问题
--只要子查询


--分页查询显示员工信息：显示员工号，姓名，月薪
--每页显示四条，按照月薪降序排列
--rownum只能使用<,<=  不能使用>,>=
select *
	from(select rownum r,empno,ename,sal 
		from (select rownum,empno,ename,sal from emp order by sal desc) e1
			where rownum<=8) e2
				where r>=5
