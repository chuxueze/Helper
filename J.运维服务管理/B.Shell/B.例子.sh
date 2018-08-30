自动提交git代码

##############################
#!/bin/bash
echo "-------Begin-------"
git add .
git commit -m $1
echo $1
git push origin master
echo "--------End--------"
##############################

调用:
./test.sh "第一次提交"