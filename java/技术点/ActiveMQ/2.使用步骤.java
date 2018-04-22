启动步骤：
1.到apche下载相应jar包（apache-activemq-5.11.1）
2.运行启动脚本D:\activeMq\apache-activemq-5.11.1\bin\win64   activemq.bat
3.登录访问管控台  user:admin password:admin http://localhost:8161/admin/ 

--------------------------------------------------------

也可以使用服务安装的方式，这样就不用得一直保留着控制台了。
D:\activeMq\apache-activemq-5.11.1\bin\win64\InstallService.bat
启动此安装脚本，在系统的服务中去启动，就像mysql一样，可以设置随开机自启动。



--------------------------------------------------------
Maven依赖
   <dependency>
       <groupId>org.apache.activemq</groupId>
       <artifactId>activemq-all</artifactId>
       <version>5.9.0</version>
   </dependency>