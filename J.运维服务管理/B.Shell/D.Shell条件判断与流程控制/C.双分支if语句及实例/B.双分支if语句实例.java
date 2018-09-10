1.判断输入的是否是目录
2.判断 Apache 服务是否启动









------------------------------------------------------------------------------------
1.判断输入的是否是目录
例子:
#!/bin/bash
read -t 30 -p "Please input a dir:" dir
if [ -d "$dir" ]
then 
	echo "is dir"
else
	echo "no dir"
fi 


----------------------------------------
2.判断 Apache 服务是否启动
思路:
2.1:先找出 httpd 相关的服务，再从中排除跟 grep 相关的进程。（因为 grep 命令本身 会产生 grep 进程）
2.2:截取进程之后，将结果赋予变量，判断变量是否为空
	如果不为空，输出服务正常，执行日志记录。
	如果为空，启动服务，输出服务重启，执行日志记录。
ps aux | grep httpd | grep -v grep

程序:
#!/bin/bash
test=$(ps aux | grep httpd | grep -v grep)
if [ -n "$test" ]
	then
	echo "httpd is OK" >> /tmp/httpd.log
else
	echo "httpd is no" >> tmp/httpd.log
	/etc/rc.d/init.d/httpd start 
fi

注意:
sh 文件命名不要包含 httpd 字符，否则 grep 命令会1直检测到 httpd 进程，导致输出 "httpd is OK"