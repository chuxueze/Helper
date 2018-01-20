SQL语句的执行顺序

手写顺序：
1.select
2.distinct
3.from 
4.join 
5.on
6.where
7.group by
8.having
9.order by
10.limit

机读顺序：(SQL本身的解析)
1.from
2.on
3.join
4.where 
5.group by
6.having
7.select 
8.distinct
9.order by
10.limit

1.from--查询笛卡尔积
2.on--主表保留
3.join--不符合on也添加
4.where --非select别名
5.group by--改变对表引用
6.having--只作用分组后
7.select --distinct
8.distinct
9.order by--可以使用select 别名
10.limit