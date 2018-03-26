步骤：
1.将dao层和service写好打成jar包
2.在注册中心将服务注册（常用的是ZooKepper）






2.在注册中心将服务注册（常用的是ZooKepper）
--
服务提供者注册例子
//和本地服务一样实现远程服务，注入
<bean id="userService" class="com.imooc.userServiceImpl"></bean>
//增加暴露远程服务配置
<dubbo:service interface="com.imooc.userService" ref="userService">
--
服务消费者注册例子
//增加引用远程服务配置
<dubbo:reference id="userService" interface="com.imooc.userService">
//和本地服务一样使用远程服务
<bean id="UserAction" class="com.imooc.UserAction">
	<property name="userService" ref="userService">
</bean>
