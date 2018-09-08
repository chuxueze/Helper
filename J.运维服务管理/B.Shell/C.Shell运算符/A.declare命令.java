1.declare 声明变量类型




------------------------------------------
1.declare 声明变量类型
declare [+/-][选项] 变量名
选项:
	-:给变量设定类型属性
	+:取消变量的类型属性
	-a:将变量声明为数组型
	-i:将变量声明为整数型(integer)
	-x:将变量声明为环境变量
	-r:将变量声明为只读变量
	-p:显示指定变量的被声明的类型

声明 cc 为整形
declare -i cc

声明 arr 为数组
[root@localhost shell]# declare -a arr
[root@localhost shell]# arr[0]=110
[root@localhost shell]# arr[1]=111
[root@localhost shell]# arr[2]=112
[root@localhost shell]# echo ${arr[*]}
110 111 112

声明 test 为环境变量
和 export 作用相似，但其实是 declare 命令的作用
declare -x test=123
