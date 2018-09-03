1.locate 命令格式











--------------------------------------------------------
1.locate 命令格式
locate 文件名
在后台数据库中按文件名搜索

/var/lib/mlocate
#locate 命令所搜索的后台数据库

updatedb
更新数据库 


  当使用 locate 时搜索文件时，会在 mlocate 数据库中进行查找
这个数据库不是实时更新的，更新频率为每天1次。如果是新增的数据，
则要执行更新数据库命令 updatedb。

locate 只可以按照文件名搜索 

cat etc/updatedb.conf
此配置文件记录着 locate 命令查找的文件，和需要过滤的文件