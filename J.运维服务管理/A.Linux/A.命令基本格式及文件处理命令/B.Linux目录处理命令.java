1.建立目录 mkdir
2.切换所在目录 cd










----------------------------------------------------------
1.建立目录 mkdir
mkdir -p [目录名]
-p 递归创建

例子:
1.1:
mkdir -p test1 test2
创建 test1 和 test2 两个目录

1.2:
mkdir -p test3/test3.1
先创建 test3 父目录，再在父目录下面创建 test3.1 子目录

1.3:
mkdir test4
直接创建 test4 目录

------------------------------
2.切换所在目录 cd
cd:change directory
cd、cd ~    进入当前用户的家目录 
cd -    进入上次目录
cd ..   进入上1级目录
cd .    进入当前目录