1.IDEA 收费版创建 SpringBoot 项目
2.三种启动方式




-------------------------------------------------------------------------------------
1.IDEA 收费版创建 SpringBoot 项目

1.1:按步骤创建项目
New Project -> Spring Initializr -> Next

Spring Initializr:
选择 default 

Next:
组件选择 Web 下的 Web

-----------------------
1.2:删除无用的东西
.mvn 文件夹
mvnw
mvnw.cmd






------------------------------------------
2.三种启动方式
2.1:
直接执行 GirlApplication.java 

2.2:
在控制台通过 Maven 命令控制
mvn spring-boot:run

2.3:
mvn install 之后，生成 jar 包，再运行 jar 包方式启动

java -jar girl-0.0.1-SNAPSHOT.jar