1.确定 at 已安装
2.at 的访问控制
3.at 命令用法
4.其他 at 管理命令






--------------------------------------------------------------------------------
1.确定 at 已安装
1般情况下不手动删除的话，都会有这个服务。

at 服务是否安装
chkconfig --list | grep atd

at 服务的启动
service atd restart



-----------------------------------------
2.at 的访问控制
    如果系统中有 /etc/at.allow 文件，那么只有写入 /etc/at.allow 文件(白名单)中的用户
可以使用 at 命令(etc/at.deny 文件会被忽略)
    如果系统中没有 /etc/at.allow 文件，只有 /etc/at.deny 文件，那么写入 /ect/at.deny 
文件(黑名单)中的用户不能使用 at 命令。对 root 不起作用。
	如果系统中这两个文件都不存在，那么只有 root 用户可以使用 at 命令。 


-----------------------------------------
3.at 命令用法
at [选项] 时间
选项: 
	-m:当 at 工作完成后，无论是否命令有输出，都用 email 通知执行 at 命令的用户
	-c 工作号:显示该 at 工作的实际内容
时间:
	-HH:MM 例如  02:30 
	-HH:MM YYYY-MM-DD 例如  02:30 2013-07-25
	-HH:MM[am|pm] [month][date] 例如  02:30 July 25
	-HH:MM[am|pm] + [minutes|hours|days|weeks] 例如  now + 5 minutes

举例:
5 分钟后执行 hello.sh 命令
at now +5 minutes 
at>/root/hello.sh

在指定时间重启
at 02:00 2013-07-26
/bin/sync
/sbin/shutdown -r now



-----------------------------------------
4.其他 at 管理命令
查询当前服务器上的 at 工作
atq

删除指定的 at 任务
atrm [工作号]