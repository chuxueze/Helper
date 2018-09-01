1.建立目录 mkdir
2.切换所在目录 cd
3.绝对路径 与 相对路径
4.查询所在目录的位置 pwd
5.删除空目录 rmdir
6.删除目录或文件 rm -rf








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

------------------------------
3.绝对路径 与 相对路径
绝对路径:
cd /user/local/src/
相对路径:
cd ../user/local/src/

------------------------------
4.查询所在目录的位置 pwd
print working directory
pwd
/user/local/src

------------------------------
5.删除空目录 rmdir
remove empty directories 

------------------------------
6.删除目录或文件 rm -rf
rm 
rm -r
-r 删除目录
-f 强制