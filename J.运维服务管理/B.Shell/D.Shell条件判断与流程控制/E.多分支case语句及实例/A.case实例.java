1.判断用户输入








---------------------------------------------------------------------------
1.判断用户输入
#!/bin/bash
read -p "Please choose yes/no:" -t 30 cho
case "$cho" in 
	"yes") 
		echo "your choose is yes"
		;;
	"no")
		echo "your choose is no"
		;;
	*)
		echo  "your choose is Error"
		;;
esac