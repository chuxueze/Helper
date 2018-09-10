1.环境变量与用户自定义变量的区别
2.设置环境变量
3.查看环境变量
4.删除环境变量
5.PATH环境变量
6.PS1环境变量





--------------------------------------------------------------------------
1.环境变量与用户自定义变量的区别

环境变量:
全局变量，
在当前 Shell 和这个 Shell 的所有子 Shell 中生效

用户自定义变量:
局部变量，
只在当前的 Shell 中生效


----------------------------------------
2.设置环境变量
export 变量名=变量值
或者
变量名=变量值
export 变量名

例子:
export z=2333

----------------------------------------
3.查看环境变量
set 
查看所有变量

env
查看环境变量

----------------------------------------
4.删除环境变量
unset 变量名

----------------------------------------
5.PATH环境变量
PATH变量:系统查找命令的路径

查看PATH环境变量
echo $PATH变量

增加PATH变量的值
PATH="$PATH":/toot/sh

增加PATH环境变量之后，就可以简单运行命令了。

----------------------------------------
6.PS1环境变量
如图: