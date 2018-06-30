    msyql的慢查询日志是mysql提供的一种日志记录，它用来记录在mysql中响应时间超过阙值的语句，
具体指运行时候超过long_query_time值的sql，则会被记录到慢查询日志中。

    默认情况下，mysql数据库没有开启慢查询日志，需要我们手动来设置这个参数
    如果不是调优需要的话，一般不建议启动该参数，因为开启慢查询日志会或多或少带来一定的性能影响。
查询日志支持将日志记录写入文件。


查看慢查询日志
show variables like '%slow_query_log%';

查看慢查询日志条数
show global status like '%slow_queries%';
 
开启慢查询日志
set global slow_query_log=1;

查看慢查询设置的时间长短
show variables like '%long_query_time%';

设置时间长短
set global long_query_time = 3;
设置之后需要重新连接或者新开一个会话，才能看到修改值


-------------------
日志分析工具
查看mysqldumpslow的帮助信息
s:是表示按照何种方式排序
c:访问次数
l:锁定时间
r:返回记录
t:查询时间
al:平均锁定时间
ar:平均返回记录数
at:平均查询时间
t:为返回前面多少条数据
g:后边搭配一个正则匹配模式，大小写不敏感


Linux系统下：
举例：
得到返回记录集最多的10个sql
mysqldumpslow -s r -t 10 D:\MySql\data\USER-20170430MS-slow.log (log的位置)

得到访问次数最多的10个sql
msyqldumpslow -s c -t 10 ...

得到按照时间排序的前10条里面含有左连接的查询语句
msyqldumpslow -s t -t 10 -g "left join"..

另外建议在使用这些命令时结合|和more使用，否则有可能出现爆屏情况
mysqldumpslow -s r -t 10 D:\MySql\data\USER-20170430MS-slow.log |more