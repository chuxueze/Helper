1.crond 服务管理与访问控制
2.用户的 crontab 设置







---------------------------------------------------------------------
1.crond 服务管理与访问控制
重启服务
service crond restart

检查服务
chkconfig crond on
chkconfig --list | grep cron
service crond status

---访问控制见图
类似 atd 命令



----------------------------------------------
2.用户的 crontab 设置
crontab [选项]
选项:
	-e:编辑 crontab 定时任务
	-l:查询 crontab 任务
	-r:删除当前用户所有的 crontab 任务



例子:
crontab -e 
进入 crontab 编辑界面。会打开 vim 编辑你的工作。

*****执行的任务

---星号含义见图
---特殊符号含义见图

每1分钟执行1次命令
* * * * * /root/sh/apache_check.sh 

每小时的10分钟执行1次命令
10 * * * * /root/sh/apache_check.sh 

每隔10分钟执行1次命令
*/10 * * * * /root/sh/apache_check.sh 