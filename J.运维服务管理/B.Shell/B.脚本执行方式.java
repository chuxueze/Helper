1.echo 命令
2.脚本执行方式




----------------------------------------------------------------
1.echo 命令

#!/bin/bash
echo " Hello World !"
直接输出字符串


#!/bin/bash
echo -e " Hello \n World !"
加 -e 实现字符控制，此命令可以实现换行


-----------------------------
2.脚本执行方式
用 vim 编写完 shell 脚本之后。需要授权才能执行。

赋予执行权限，直接运行
chmod 755 hello.sh
./hello.sh

通过 Bash 调用执行脚本
bash hello.sh