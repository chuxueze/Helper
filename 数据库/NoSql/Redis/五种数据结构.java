1.字符串(String)
2.字符串列表(list)
3.有序字符串集合(sorted set)
4.哈希(hash)
5.字符串集合(set)


1.字符串(String)
1.1:value最多可容纳512M
1.2: 二进制安全的，存入和获取的数据相同
1.3:命令：
    redis 127.0.0.1:6379> SET name "runoob"
	OK
	redis 127.0.0.1:6379> GET name
	"runoob"