1.运行应用例子:
2.docker 里运行 mysql:


-----------------------------------------------------------------
1.运行应用例子:

1.1:从网易镜像中心查找并下载 tomcat docker 镜像
docker pull hub.c.163.com/library/tomcat:latest

---------------
1.2:编写 Dockerfile

from hub.c.163.com/library/tomcat
MAINTAINER zhc 895877057@qq.com
COPY jprees.war /usr/local/tomcat/webapps 

---------------
1.3:构建镜像 .为 Dockerfile 的目录
docker build -t jprees:latest .

---------------
1.4:运行镜像成为容器
docker run -d -p 8888:8080 jprees


--------------------------------
2.docker 里运行 mysql:

2.1:从镜像中心查找 mysql 镜像并下载
docker pull hub.c.163.com/library/mysql:latest

---------------
2.2:设定 mysql 的配置，可以参考镜像中心使用说明
-e:可以进行数据库的配置
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=jprees hub.c.163.com/library/mysql:latest

最后:重启容器
docker restart