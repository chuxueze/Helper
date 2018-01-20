admin
111111

1.main方法启动
2.jar包方法启动
3.war包方法启动



1.main方法启动
运行
/guns-admin/src/main/java/com/stylefeng/guns/GunsApplication.java



2.jar包方法启动

2.1.需要用到maven命令 mvn clean package -Dmaven.test.skip=true
2.2.在项目的target下找到项目的jar包
2.3.打开cmd，java -jar jar包


3.war包方法启动
也就是tomcat启动方式
3.1.将pom.xml改成war包形式，需要用到maven命令 mvn clean package -Dmaven.test.skip=true
3.2.将war包放到tomcat中


注
前两种方式访问系统时，不需要输入端口，最后一种输入输入对应的端口号


