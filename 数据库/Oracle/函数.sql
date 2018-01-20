oracle函数：
--数值函数
--字符函数
--日期函数
--转换函数
----------------------------------------------
数值函数：
--四舍五入:--ROUND(n,m)
--m>0:小数点后m位  m<0:小数点前m位
select round(2222.222), round(2222.222, 1), round(2222.222, -1) from dual
--结果：2222	2222.2	2220

--取整函数
--1.ceil(n)取整加1  2.floor(n)取整减1
select ceil(2222.222), floor(2222.222) from dual
--结果：2223	2222

--常用计算
--1.abs(s)：取绝对值  
select abs(2222.222), abs(-2222.222), abs(0) from dual
--结果：2222.222	2222.222	0
--2.mod(m,n)  取余数 m/n的余数，如果m或n为null，结果则为null
select mod(2222.222,2) from dual
--结果：0.222	
--3.power(m,n)  返回m的n次幂
select power(2,3) from dual
--结果: 8   （2的三次方）
--4.sqrt(n) 求平方根
select sqrt(16) from dual
--结果：4 

--三角函数
--sin(n),asin(n),cos(n),acos(n),tan(n),atan(n)

----------------------------------------------
字符函数：
--1.大小写转换函数
--upper(char)小写变大写  lower(char)大写变小写  initcap(char)首字母大写
select upper('zhanghaochun'), lower('ZHANGhaochun'), initcap('zhanghaochun')
  from dual
--结果：ZHANGHAOCHUN	zhanghaochun	Zhanghaochun
--应用场景：登录时不区分大小写

--2.获取子字符串函数
--substr(char,m,n)  m:取子串的开始位置  n:截取子串的位数
select substr('zhanghaochun', 6, 3) from dual
--结果：hao   (从第6个开始截取，截取3个)

--3.获取字符串长度函数
--length(char)
select length('zhanghaochun') from dual
--结果：12
--应用场景：查找名字超过三个的人的信息

--4.字符串连接函数
--concat(char1,char2)
--与||操作符的作用一样
select concat('中华', '人民'), '中华' || '人民' from dual
--结果：中华人民	中华人民

--5.去除子串函数
--5.1：trim(c2 from c1)  表示从c1中去除掉 c2 这个字符串
select trim('z' from 'zhanghaochun') from dual;
--结果：hanghaochun  仅能截取掉一个字符，第一个字符
--5.2：ltrim(c1,c2)  从左边去除
--5.3：rtrim(c1,c2)  从右边去除
--5.4：trim(c1)  去除两边的空格

--6.替换函数
--replace(char,s_string1,s_string2)   s1:需要替换的内容  s2:替换成什么内容
--s_string2为空时，默认用空格替换
select replace(' ', 'a', 'A') from dual;
--结果：AAbbAAccAAdd    将a换成A

----------------------------------------------
日期函数：
--系统时间
--sysdate
select sysdate today from dual;
--结果：2017/5/22/周一 15:46:42

--日期操作
--1.add_months(date,i)   i：想要加上几个月，为整数。如果是小数，则截取整数部分
--作用：正数：返回在指定日期上增加几个月份  负数：返回在指定日期上减少几个月份
select add_months(sysdate, 3), add_months(sysdate, -3) today from dual;
--结果：2017/8/22/周二 15:55:39	2017/2/22/周三 15:55:39

--2.next_day(date,char)
--作用：如果char的值是'星期一'，则返回date指定日期的下周一是哪天
select sysdate, next_day(sysdate, '星期一') from dual;
--结果：2017/5/22/周一 16:04:53	  2017/5/29/周一 16:04:53

--3.last_day(date)
--作用：返回所在月的最后一天
select sysdate, last_day(sysdate) from dual;
--结果：2017/5/22/周一 16:09:14	2017/5/31/周三 16:09:14

--4.months_between(date1,date2)
--作用：计算两个日期之间相隔的月份
select months_between(add_months(sysdate, 12), sysdate) from dual;
--结果：12    等同于两个日期的值直接相减

--5.extract(date from datetime)
--作用：从datetime里找出date的时间
select sysdate,--2017/5/22/周一 16:23:36
       extract(year from sysdate),--2017
       extract(month from sysdate),--5
       extract(day from sysdate)--22
  from dual;
--结果：2017/5/22/周一 16:23:36	2017 5	22
--hour minites 

--统计季度
select to_char(sysdate,'Q') from dual;

Select to_char(sysdate,'Q') from dual;--指定日期的季度
Select to_char(sysdate,'MM') from dual;--月份
Select to_char(sysdate,'WW') from dual;--当年第几周
Select to_char(sysdate,'W') from dual ;--本月第几周
Select to_char(sysdate,'DD') from dual;--当月第几天
Select to_char(sysdate,'D') from dual;--周内第几天
Select to_char(sysdate,'DY') from duaL;--星期几
Select last_day(sysdate) from dual;--本月最后一天
Select add_months(sysdate,2) from dual;--当前日期d后推n个月 
select months_between(sysdate,to_date('2012-11-12','yyyy-mm-dd'))from dual;--日期f和s间相差月数
SELECT (next_day(sysdate,1)+1) FROM dual;--指定的日期之后的第一个工作日的日期
select to_char(add_months(last_day(sysdate),-1),'yyyy-MM-dd') LastDay from dual;--上月末天
select to_char(add_months(sysdate,-1),'yyyy-MM-dd') PreToday from dual;--上月今天
select to_char(add_months(last_day(sysdate)+1,-2),'yyyy-MM-dd') firstDay from dual;--上月第一天
select to_char(sysdate,'ww') from dual group by to_char(sysdate,'ww');--按照每周进行统计
select to_char(sysdate,'mm') from dual group by to_char(sysdate,'mm');--按照每月进行统计
select to_char(sysdate,'q') from dual group by to_char(sysdate,'q');--按照每季度进行统计


----------------------------------------------
转换函数：
--1.日期转换成字符
--to_char(date,fmt)  date:将要转换的日期  fmt:转换的格式
select to_char(sysdate, 'yyyy-MM-dd  HH24:MI:ss') from dual;
--结果：2017-05-23  09:37:25

--2.字符转换成日期
--to_date(char,fmt) char:要转换的字符串  fmt:转换的格式
select to_date('2017-05-23', 'yyyy-MM-dd') from dual;
--结果：2017/5/23/周二     只会按照系统默认格式显示日期

--3.数字转换成字符
--to_char(number,fmt)  number：所要转换的数字类型数据  fmt:所要的格式数据
select to_char(123456.789, '$999,999.999') from dual
--结果： $123,456.789
select to_char(123456.789, 'S999,999.999') from dual
--结果：+123,456.789

--4.字符转换成数字
--to_number(char,fmt)  char:想要转换的字符  fmt：想要的格式 
select to_number('$1,000','$9999') from dual;
--结果：1000


--fmt日期格式：
--年:YY  YYYY  YEAR
--月:MM  MONTH
--日:DD  DAY
--时:HH24  HH12
--分秒:MI  SS

--fmt数字转字符格式：
--9：显示数字并忽略前面的0
--0：显示数字，位数不足，用0补齐
--. 或 D:显示小数点
--，或 G:显示千位符
--$：美元符号
--S：加正负号（前后都可以）


--其他函数：

--nvl(a,b)
--如果a为空，则显示b，如果不为空，则显示a
--类似于三元运算符
select nvl('','数据为空') from dual;
--结果：数据为空
--场景：
--当执行插入数据时，字段约束不能为空，就可以用该函数给一个默认值

--DECODE(value,if1,then1,if2,then2,if3,then3,...,else)
select decode(100,10,'值是10','30','值为30','50','值为50','10000') from dual;
--结果：10000
--switch(value){if1:then1;if2:then2;if3:then3;else}
--场景：
--执行复杂判断的时候可以用到


--case when if1 then end1  else default end
select case
         when 100 = 10 then
          '值为10'
         when 100 = 100 then
          '值为100'
         when 100 = 1000 then
          '值为1000'
         else
          '默认值'
       end
 from dual;
----------------
 select case 100
         when  10 then
          '值为10'
         when 100 then
          '值为100'
         when  1000 then
          '值为1000'
         else
          '默认值'
       end
 from dual;
--结果：值为100
--场景，执行复杂判断时
--运用例子（统计符合特定条件的费用信息）
sum(case when d.bka432 not in ('101','102') and d.bka078 in('C001','C002','C003') and d.aaa157 = '999' then d.aae019 else 0 end ) BKE108--统筹支付费用

