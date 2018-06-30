D:\ZooKeeper\zookeeper-3.4.12\conf
zoo.cfg


集群环境搭建需要三个服务器地址
IP 不同，端口可用就行
server.1=127.0.0.1:2888:3888
server.2=127.0.0.2:2888:3888
server.3=127.0.0.3:2888:3888

伪集群
IP 相同，端口不同且可用
server.1=127.0.0.1:2888:3888
server.2=127.0.0.1:2889:3889
server.3=127.0.0.1:2890:3890


启动时，通过bin中的
zkService.sh


启动:zkService.sh start
停止:zkService.sh stop

windows可以直接打开  zkService.cmd