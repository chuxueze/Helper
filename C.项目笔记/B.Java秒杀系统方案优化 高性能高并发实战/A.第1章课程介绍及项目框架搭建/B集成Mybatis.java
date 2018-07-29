1.添加依赖及配置项
2.连接池 druid 配置









---------------------------------------------------
1.添加依赖及配置项
http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
pom.xml 加入
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>

application.properties 文件中加入配置
#扫描包位置
mybatis.type-aliases-package=com.miaosha.domain
#下划线转换驼峰
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.default-fetch-size=100
mybatis.configuration.default-statement-timeout=3000
#xml文件位置
mybatis.mapper-locations=classpath:com/imooc/miaosha/dao/*.xml      */

-------------------------
2.连接池 druid 配置
application.properties 配置
#druid jdbc配置
spring.datasource.url=jdbc:mysql://localhost:3306/miaosha?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&autoReconnect=true&failOverReadOnly=false
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driverClassName=com.mysql.jdbc.Driver
#druid jdbc配置

#druid 连接池配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.filters=stat
spring.datasource.maxActive=2
spring.datasource.initialSize=1
spring.datasource.max-wait=60000
spring.datasource.minldel=1
spring.datasource.time-between-eviction-runs-millis=60000
spring.datasource.minEvictableTimeMillis=300000
spring.datasource.validation-query=select 'x'
#druid 连接池配置

----------
pom 依赖
      <!--mysql-->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.4</version>
      </dependency>
      <!--mysql-->
      <!--druid-->
      <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>druid-spring-boot-starter</artifactId>
          <version>1.1.10</version>
      </dependency>
      <!--druid-->