1.inner join(内连接)
2.left join(左连接)右连接类似


--------------------
1.inner join(内连接)
select * from A inner join B on A.id=B.id;
取两表公有的部分。前提条件是 on 条件成立

2.left join(左连接)
select * from A left join B on A.id=B.id;
取A全表数据和AB两表公共的数据。

3.left join(左连接)
select * from A left join B on A.id=B.id where B.id is null;
取A表数据，去除AB两表公共的部分。