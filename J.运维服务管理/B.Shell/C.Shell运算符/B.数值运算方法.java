1.数值运算方法













-----------------------------------------------------------------
1.数值运算方法
1.1:方法1
[root@localhost shell]# aa=11
[root@localhost shell]# bb=22
[root@localhost shell]# declare -i cc=$aa+$bb
[root@localhost shell]# echo $cc
33

1.2:方法2
expr 或 let 数值运算工具
[root@localhost shell]# dd=$(expr $aa + $bb)
[root@localhost shell]# echo $dd
33

1.3:方法3
"$((运算式))" 或 "$[运算符]"
[root@localhost shell]# ff=$(($aa+$bb))
[root@localhost shell]# gg=$[$aa+$bb]
[root@localhost shell]# echo $gg
33
[root@localhost shell]# echo $ff
33
[root@localhost shell]# 
