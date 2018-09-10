1.判断当前用户是否为 root
2.判断分区使用率









-------------------------------------------------------------------------
1.判断当前用户是否为 root

思路:
1.1:找到环境变量 USER 行 --env | grep "USER"
1.2:截取行中用户名 --cut -d "=" -f2
1.3:判断是否等于 root


程序:
#!/bin/bash
userName=$(env | grep "USER" | cut -d "=" -f2)
if [ "$userName" == root ]
  then
  echo "user is root"
fi


--------------------------------------
2.判断分区使用率
awk:举例
# 每行按空格或TAB分割，输出文本中的1、4项
$ awk '{print $1,$4}' log.txt

思路:
2.1:先列出分区使用情况 df -h
2.2:找到唯1标识分区行的值 grep tmpfx
2.3:找到行的第5列 awk '{print $5}'
2.4:以 '%' 为分割符，取第1个值
df -h | grep tmpfs | awk '{print $5}' | cut -d "%" -f 1

程序:
#!/bin/bash
test=$(df -h | grep /dev/xvdc1 | awk '{print $5}' | cut -d "%" -f 1)
if [ "$test" -ge "90" ]
	then
echo "已超出容量"
fi
