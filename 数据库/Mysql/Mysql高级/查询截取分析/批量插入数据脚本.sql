利用函数，存储过程实现批量存储功能

创建函数，如果报错:This function has none of DETERMINISTIC
#由于开启过慢查询日志，因为我们开启了bin-log,我们就必须为我们的function指定一个参数。
查看函数创建是否可用
show variables like 'log_bin_trust_function_creators';

设置函数创建为可用
set global log_bin_trust_function_creators = 1;

这样添加了参数之后，如果重启mysql，上述参数又会消失。
永久方法：
windows：my.ini 加上log_bin_trust_function_creators = 1
linux：/etc/my.cnf 下加上log_bin_trust_function_creators = 1


create table emp (
empno int,
enmae VARCHAR(255),
job VARCHAR(255),
mgr int,
hiredate VARCHAR(255),
sal int,
comm int,
deptno VARCHAR(255)
)
随机字符串函数
delimiter $$
create function rand_string(n int) returns varchar(255)--定义函数名
begin 
declare chars_str varchar(100) default '0123456789abcdefghijklmnopqrstuvwxyz';--变量定义
declare return_str 	varchar(255) default '';
declare i int default 0;
while i < n do 
set return_str = concat(return_str,substring(chars_str,floor(1+rand()*72),1));
i = i + 1;
end while;
return return_str;
end $$;

select rand_string(10) from dual;

指插入数据存储过程
delimiter $$
create procedure insert_emp(in start int(10),in max_num int(10))
  begin
	declare i int default 0;
	set autocommit = 0;#关闭自动提交功能，否则会每插入一条数据，就坚提交一次
	repeat
	set i = i+1;
	insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values ((start+i),rand_string(6),'salesman',0001,curdate(),2000,400,rand_string(6));
	until i = max_num
	end repeat;
	commit;
  end $$