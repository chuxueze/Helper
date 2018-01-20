小表驱动大表，也就是小的数据集驱动大的数据集
如:
select * from A where id in(select id from B)
等价于:
for:
select id from B
for:
select * from A where A.id=B.id

当B表的数据集必须小于A表的数据集时，用in优于exists


--------------------------------------------------
select * from A where exists(select 1 from B where B.id=A.id)
等价于:
for:
select * from A
for:
select * from B where B.id = A.id

当A表的数据集小于B表的数据集时，用exists优于in
此语句也可以理解为：
    将主查询的数据，放到子查询中做条件验证，
根据验证结果（true or false）来决定主查询的数据结果是否得以保留

提示：
1.exists(subquery)只返回true or false，因此子查询中的select * 或其他没有区别
2.exists子查询的实际执行过程可能经过了优化而不是我们理解上的逐条对比，如果担心效率问题，可进行实际检验以确定是否有效率问题。
3.exists子查询往往也可以用条件表达式，其他子查询或者join来替代，哪种最优需要具体问题具体分析