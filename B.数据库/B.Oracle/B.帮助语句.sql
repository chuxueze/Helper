--找出表中有此中文字符的表信息
select * from user_col_comments where comments like '%就医地%'  

--找出包含指定字段的表信息
SELECT *
  FROM USER_TAB_COLUMNS
 WHERE COLUMN_NAME like '%aaz216%'

--找出指定表中的所有字段
SELECT * FROM user_tab_columns WHERE TABLE_NAME='ACC3';


--找出两个表中有相同字段的语句 
--一般是大写
 select  * from 
 (SELECT * FROM user_tab_columns WHERE TABLE_NAME='AF05') a,
 (SELECT * FROM user_tab_columns WHERE TABLE_NAME='KC21') b
 where a.column_name = b.column_name


--找出指定哪些字段在存在哪些表下
select * from 
(SELECT *
  FROM USER_TAB_COLUMNS
 WHERE COLUMN_NAME like '%AAE002%') a,
(SELECT *
  FROM USER_TAB_COLUMNS
 WHERE COLUMN_NAME like '%AAB301%') b
 where a.table_name =  b.table_name

