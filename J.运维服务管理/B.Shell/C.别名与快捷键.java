1.命令别名与快捷键
2.别名永久生产与删除别名
3.常用快捷键





-----------------------------------------------------------
1.命令别名与快捷键

1.1:查看系统中所有的命令别名
alias


1.2:设置别名
alias 别名='原命令'

alias ls='ls --color=never'
为 ls 设置别名为 ls --color=never
当执行 ls 时，相当于执行 ls --color=never



------------------------------
2.别名永久生产与删除别名

写入环境变量配置文件
vi ~/.bashrc

删除别名
unalias 别名

------------------------------
3.常用快捷键
见图片
