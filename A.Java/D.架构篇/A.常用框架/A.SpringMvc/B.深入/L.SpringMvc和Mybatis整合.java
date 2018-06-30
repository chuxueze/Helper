详情请看
springmvc_mybatis1208
这个工程

-------思路：
 表现层： springMvc
 业务层： service接口
 持久层： mybatis

 spring 将各层进行整合，


 通过spring管理持久层的mapper（相当于dao接口），
 spring进行事务控制，
 通过spring管理业务层service，service中可以调用mapper接口，

 通过spring管理表现层 Handler,Handler 中可以调用service接口，

 mapper, service ,Handler 都是 javaBean
步骤：
第一步：整合dao层
	mybatis和spring整合，通过spring管理mapper接口。
	使用mapper的扫描器自动扫描mapper接口在spring中进行注册。

第二步：整合service层
	通过spring管理 service接口。
	使用配置方式将service接口配置在spring配置文件中。
	实现事务控制。

第三步：整合springmvc
	由于springmvc是spring的模块，不需要整合。


-------工程结构：
数据库驱动包：mysql5.1
mybatis的jar包
mybatis和spring整合包
log4j包
dbcp数据库连接池包
spring3.2所有jar包
jstl包

/springmvc_mybatis1208/config/mybatis/sqlMapConfig.xml
/springmvc_mybatis1208/config/spring/applicationContext-dao.xml
/springmvc_mybatis1208/config/spring/applicationContext-service.xml	
/springmvc_mybatis1208/config/spring/applicationContext-transaction.xml
/springmvc_mybatis1208/config/spring/springmvc.xml

新建一个sourceFolder,名称为config：
db.properties
lOg4j.properties

sqlMapConfig.xml
设置别名，全局settings

applicationContext-transaction.xml
Aop 通知，传播行为，数据源，事务管理器

applicationContext-service.xmml
配置service类的bean，将bean注入ioc容器中,
<bean id="itemsService" class="cn.itcast.ssm.service.impl.ItemsServiceImpl"/>

applicationContext-dao.xml
配置sqlSessionFactory,dataSource,mapper扫描器等
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<!-- 扫描包路径，如果需要扫描多个包，中间使用半角逗号隔开 -->
	<property name="basePackage" value="cn.itcast.ssm.mapper"></property>
	<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
</bean>

springmvc.xml
配置扫描包，映射器，适配器，视图解析器，参数绑定