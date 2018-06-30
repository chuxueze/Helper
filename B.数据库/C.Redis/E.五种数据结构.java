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
------------------------
2.字符串列表(list)
2.1:
	命令：
	设值：
	LPUSH langueList Java
	LPUSH langueList C++
	LPUSH langueList C
	LPUSH langueList PHP
	取值：
	LRANGE langueList 0 10 
	LRANGE langueList 0 -1
	1) "PHP"
	2) "C"
	3) "C++"
	4) "Java"


------------------------
4.哈希(hash)
4.1:
    适合存储对象，使用比较广泛
4.1:命令：
    hset myhash field1 hello2
    hset myhash field2 hello2
    hget myhash field1