1.常用目录的作用 








-----------------------------------------------
drwxr-xr-x.   3 root root   77 Nov 14  2017 activeMq
lrwxrwxrwx.   1 root root    7 Oct  1  2017 bin -> usr/bin
dr-xr-xr-x.   4 root root 4096 Oct  1  2017 boot
drwxr-xr-x.  20 root root 3200 Sep  1 06:49 dev
drwxr-xr-x.  78 root root 8192 Sep  1 08:06 etc
drwxr-xr-x.   2 root root    6 Nov  5  2016 home
lrwxrwxrwx.   1 root root    7 Oct  1  2017 lib -> usr/lib
lrwxrwxrwx.   1 root root    9 Oct  1  2017 lib64 -> usr/lib64
drwxr-xr-x.   2 root root    6 Nov  5  2016 media
drwxr-xr-x.   2 root root    6 Nov  5  2016 mnt
drwxr-xr-x.   2 root root    6 Nov  5  2016 opt
dr-xr-xr-x. 111 root root    0 Sep  1 06:49 proc
-rw-r--r--.   1 root root    0 Nov 15  2017 profile
dr-xr-x---.   3 root root  160 Nov 15  2017 root
drwxr-xr-x.  22 root root  640 Sep  1 06:49 run
lrwxrwxrwx.   1 root root    8 Oct  1  2017 sbin -> usr/sbin
drwxr-xr-x.   2 root root    6 Nov  5  2016 srv
dr-xr-xr-x.  13 root root    0 Sep  1 06:49 sys
drwxr-xr-x.   5 root root  111 Sep  1 08:28 test
drwxrwxrwt.  15 root root 4096 Sep  1 07:22 tmp
drwxr-xr-x.  14 root root  167 Nov 15  2017 usr
drwxr-xr-x.  19 root root  267 Sep  1 06:49 var


1.常用目录的作用 
/ 根目录
/bin 命令保存目录(普通用户就可以读取的命令)
/boot 启动目录，启动相关文件
/dev 设备文件保存目录
/etc 配置文件保存目录
/home 普通用户的家目录
/lib 系统库保存目录
/mnt 系统挂载目录
/media 挂载目录
/root 超级用户的家目录
/tmp 临时目录
/sbin 命令保存目录(超级用户才能使用的目录)
/proc 直接写入内存的
/sys 
/usr 系统软件资源目录
/usr/bin/ 系统命令(普通用户)
/user/sbin/ 系统命令(超级用户)
/var 系统相关文档内容

如果做练习,1 般在家目录 root 或 home,以及 tmp 目录下随便放内容。