1.判断用户输入的是什么文件










---------------------------------------------------------------------------------
1.判断用户输入的是什么文件
#!/bin/bash
read -p "Please input a fileName:" file
if [ -z "$file" ]
	#判断 file 变量是否为空
	then 
	echo "Error,please input a fileName"
	exit 1
elif [ ! -e "$file" ]
	#判断 file 的值是否存在
	then 
	echo "Your input is not a file!"
	exit 2
elif [ -f "$file" ]
    #判断 file 的值是否为普通文件
   	then 
   	echo "$file  is a regulare file!"
elif [ -d "$file" ]
	#判断 file 的值是否为目录文件
	then
	echo "$file is a directory!"
else 
	echo "$file is an other file!"
fi 