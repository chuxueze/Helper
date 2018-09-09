1.判断当前用户是否为 root











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
