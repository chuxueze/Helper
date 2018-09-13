1.进程的查看 ps
2.ps aux 进程信息讲解
3.pstree 查看进程树





--------------------------------------------------------------------------------------------
1.进程的查看 ps
ps aux 
查看系统中所有进程，使用 BSD 操作系统格式

ps -le
查看系统中所有进程，使用 Linux 标准命令格式

选项:
-a:显示1个终端的所有进程，除了会话引线
-u:显示进程的归属用户及内存的使用情况
-x:显示没有控制终端的进程
-l:长格式显示。显示更加详细的信息
-e:显示所有进程，和 -A 作用1致



--------------------------------------------------
2.ps aux 进程信息讲解

USER:该进程是由哪个用户生产的;
PID:进程的ID号;
%CPU:该进程占用CPU资源的百分比，占用越高，进程越耗费资源;
%MEM:该进程占用物理内存的百分比，占用越高，进程越耗费资源;
VSZ:该进程占用虚拟内存的大小，单位KB;
RSS:该进程占用实际物理内存大小，单位KB;
TTY:该进程是在哪个终端中运行的。其中 tty1-tty7 代表本地控制台终端，tty1-tty6 是本地
    的字符界面终端，tty7 是图形终端。pts/0-255 代表虚拟终端。 
STAT:进程状态。常见的状态有:
	R:运行
	S:睡眠
	T:停止
	s:包含子进程
	+:位于后台
START:该进程的启动时间
TIME:该进程占用CPU的运算时间，注意不是系统时间
COMMAND:产生此进程的命令名 



ps aux 例子
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0  22124  1644 ?        Ss   09:11   0:00 /bin/bash /etc/shiyanlou/sbin/init.sh
shiyanl+    27  0.0  0.4 254336 39336 ?        Sl   09:11   0:00 /usr/bin/Xvnc :1 -auth /home/shiyanlou/.Xauthority -desktop 860cb744ab7e:1 (shiyanlou) -fp /usr/share/fonts/X11//misc,/usr/share/fonts/X11//Type1 -geometry 1152x864 -pn -rfbauth /home/shiyanlou/.vnc/passwd -rfbport 5901 -rfbwait 30000
shiyanl+    46  0.0  0.0   4492   640 ?        S    09:11   0:00 /bin/sh /home/shiyanlou/.vnc/xstartup
shiyanl+    47  0.0  0.0   4492   692 ?        S    09:11   0:00 /bin/sh /etc/xdg/xfce4/xinitrc -- /etc/X11/xinit/xserverrc
root        49  0.0  0.1  52212 15584 ?        S    09:11   0:00 /usr/bin/python /usr/bin/supervisord -n
shiyanl+    60  0.0  0.1 261876 10232 ?        Sl   09:11   0:00 xfce4-session
shiyanl+    63  0.0  0.0  43720   832 ?        S    09:11   0:00 /usr/bin/dbus-launch --sh-syntax --exit-with-session xfce4-session
shiyanl+    64  0.0  0.0  43016  1468 ?        Ss   09:11   0:00 /usr/bin/dbus-daemon --fork --print-pid 5 --print-address 7 --session
shiyanl+    66  0.0  0.0  47628  2660 ?        S    09:11   0:00 /usr/lib/x86_64-linux-gnu/xfce4/xfconf/xfconfd
shiyanl+    72  0.0  0.0  11128   312 ?        Ss   09:11   0:00 /usr/bin/ssh-agent -s
shiyanl+    74  0.0  0.1 184468 10464 ?        S    09:11   0:00 xfwm4
shiyanl+    78  0.0  0.1 364312 13772 ?        Sl   09:11   0:00 xfce4-panel
shiyanl+    80  0.0  0.0 169040  6840 ?        S    09:11   0:00 Thunar --daemon
shiyanl+    81  0.0  0.1 368116  9176 ?        Ssl  09:11   0:00 xfsettingsd
shiyanl+    83  0.0  0.2 482980 19004 ?        Sl   09:11   0:00 xfdesktop
root        85  0.0  0.0  65496  3244 ?        S    09:11   0:00 /usr/sbin/sshd -D
shiyanl+    89  0.0  0.0 444720  6856 ?        Ssl  09:11   0:00 xfce4-volumed
shiyanl+    94  0.0  0.0 275844  3476 ?        Sl   09:11   0:00 /usr/lib/gvfs/gvfsd
shiyanl+   107  0.0  0.0 328452  3716 ?        Sl   09:11   0:00 /usr/bin/pulseaudio --start --log-target=syslog
shiyanl+   110  0.0  0.0  62724  2964 ?        S    09:11   0:00 /usr/lib/x86_64-linux-gnu/gconf/gconfd-2
shiyanl+   119  0.0  0.0 148896  5512 ?        S    09:11   0:00 /usr/lib/x86_64-linux-gnu/xfce4/notifyd/xfce4-notifyd
shiyanl+   120  0.0  0.0 157876  7540 ?        S    09:11   0:00 /usr/lib/x86_64-linux-gnu/xfce4/panel/wrapper-1.0 /usr/lib/x86_64-linux-gnu/xfce4/panel/plugins/libsystray.so 6 10485791 systray 通知区域 通知?标出现的区域
shiyanl+   130  0.0  0.0 273628  3328 ?        Sl   09:11   0:00 /usr/lib/gvfs/gvfs-udisks2-volume-monitor
root       137  0.0  0.0  92812  3992 ?        Ss   09:11   0:00 sshd: shiyanlou [priv]
shiyanl+   146  0.0  0.0  92812  1896 ?        S    09:11   0:00 sshd: shiyanlou@pts/0
shiyanl+   147  0.2  0.0  47944  4404 pts/0    Ss   09:11   0:00 -zsh
shiyanl+   240  0.0  0.0  38496  1644 pts/0    R+   09:18   0:00 ps aux

--------------------------------------------------

3.pstree 查看进程树
pstree
-p:显示进程的PID
-u:显示进程的所属用户

pstree
init.sh-+-Thunar
        |-Xvnc-+-{llvmpipe-0}
        |      |-{llvmpipe-1}
        |      |-{llvmpipe-2}
        |      `-{llvmpipe-3}
        |-dbus-daemon
        |-dbus-launch
        |-gconfd-2
        |-gvfs-udisks2-vo-+-{gdbus}
        |                 `-{gmain}
        |-gvfsd-+-{gdbus}
        |       `-{gmain}
        |-pulseaudio---{null-sink}
        |-sh---sh---xfce4-session---{gmain}
        |-ssh-agent
        |-supervisord---sshd---sshd---sshd---zsh---pstree
        |-xfce4-panel-+-panel-6-systray
        |             |-{gdbus}
        |             `-{gmain}
        |-xfce4-volumed-+-{gdbus}
        |               `-{gmain}
        |-xfconfd
        |-xfdesktop-+-{gdbus}
        |           `-{gmain}
        |-xfsettingsd-+-{gdbus}
        |             `-{gmain}
        `-xfwm4