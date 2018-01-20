------------------------
文档帮助命令
命令:man
语法:man[命令配置文件]
描述:帮助命令，可以获得命令的帮助文档，如何使用等。

命令:whatis
语法:whatis[命令]
描述:查看命令的描述

命令:--help
语法:[命令]--help
描述:查看命令的选项用法

man ll
whatis ls
find --help

clear:格式化清屏
vim 加文件位置:编辑文件
:q : vim下退出 
pwd:查看当前所在路径
ls:查看当前目录内容
ll:使用命令查看详细信息

-----------------------------
文件处理命令:

ls:显示文件目录
ls -a:显示所有文件，包括隐藏文件
ls -l:显示详细信息


touch [文件名]:创建一个空文件


mkdir [文件夹名]:创建一个文件夹
批量创建:
mkdir -p d1/d2/d3/d4
创建四个文件夹


cp(copy):复制文件或目录
cp  [源文件] [目标文件]
cp  -r [源目录] [目标目录]


rm(remove):删除文件或目录
rm -f[文件]
rm -r[目录,文件夹]
rm -rf[目录，包含目录下内容]


cat:查看文件内容（内容过多，显示内容可能不全）
cat [文件名称]

more:查看文件内容，可完全显示
more[文件名称]
使用空格进行翻页，回车显示下一行，q退出


head:查看文件的前多少行
head -number [文件名]

tail:查看文件的后多少行
tail -number [文件名]

mv
1.移动文件名称的功能:mv[源文件名称][新文件名称]
2.移动文件位置:mv[源文件名称] [新文件位置 新文件名称]



软链接和硬链接
  软链接特点：权限是所有人都可以访问，并且软链接文件指向源文件。
软链接就像windows系统中的快捷方式一样，特点也类似。
  硬链接：类似copy,硬链接大小和源文件一样，并且是同步更新的。
软链接:
ln -s 源文件名称 目标文件名称
硬链接:
ln 源文件名称 目标文件名称



权限处理命令
chmod
chmod [{ugo}{+-=}{rwx}] [文件或目录]
chmod [mode=421] [文件目录]

ugo:user group orders
如：chmod u + x a.html
为用户加上执行的权限

rwx:421
-rw-r--r--. 1 root root 37 9月  21 21:34 a.html
chmod 777 
-rwxrwxrwx. 1 root root 37 9月  21 21:34 a.html
chmod 641 a.html
-rw-r----x. 1 root root 37 9月  21 21:34 a.html


 切换用户:
 su:管理员
 su - [用户名]

 
 改变文件的所有者
 命令:chown
 语法:chown user 文件

 
 改变文件的属性组
 命令:chgrp
 语法:chgrp group 文件


文件查找命令
find /etc -mmin -120
查找etc目录中120分钟内被修改过的文件

时间查找
天：  ctime、atime、mtime
分钟：cmin、amin、mmin
c：change改变文件属性的意思（比如所有者，所属组，权限变更等）
a：access表示被访问过后意思（比如被查看过等）
m：modify更改内容的意思


--------------------
压缩解压缩命令:
