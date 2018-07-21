1.步骤








--------------------------------------------
1.步骤
1)pom依赖 
先排除starter-logging（因为springboot默认使用的logback框架）

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>


-------------------
2)在工程的resource下创建log4j2.xml

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT" follow="true">
            <PatternLayout>
                <pattern>%d{yyyy-MM-dd HH:mm:ss}</pattern>
            </PatternLayout>
        </Console>
        <File name="File" fileName="logs/marketing.log" append="true">
            <PatternLayout>
                <pattern>%d{yyyy-MM-dd HH:mm:ss}</pattern>
            </PatternLayout>
        </File>
    </Appenders>
    <Loggers>
        <Logger name="org.apache.catalina.util.LifecycleBase" level="error" />
        <Logger name="org.apache.coyote.http11.Http11NioProtocol" level="warn" />
        <Logger name="org.apache.tomcat.util.net.NioSelectorPool" level="warn" />
        <Logger name="org.hibernate.validator.internal.util.Version" level="warn" />
        <Logger name="org.springframework" level="info" />
        <Logger name="com.github" level="debug" />
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>

--------------------------
3)代码中使用
private static final Logger LOG = LoggerFactory.getLogger(Activity.class);
LOG.info("创建的活动id："+create_id);
