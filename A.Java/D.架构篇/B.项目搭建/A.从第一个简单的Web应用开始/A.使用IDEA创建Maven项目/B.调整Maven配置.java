1.基本与常用配置:
2.说明:









--------------------------------------------------
1.基本与常用配置:
1)基本配置:
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.smart4j</groupId>
    <artifactId>chapter1</artifactId>
    <version>1.0-SNAPSHOT</version>
</project>

-------

2)加上常用配置
编码格式
JDK版本
maven 打包跳过单元测试

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.smart4j</groupId>
    <artifactId>chapter1</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--常量定义-->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.3</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <!--maven 打包跳过单元测试-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.18.1</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>


--------------------
2.说明:
  所有的 plugin 都需要添加到 build/plugins 标签下，Maven 插件或下文中所出现的 Maven 插件或下文中所出现的
Maven 依赖都来自于 Maven 中央仓库，可以通过以下链接访问:
  http://search.maven.org/