1.非注解映射器适配器
2.注解映射器适配器
--------------------------------------------
1.1:非注解映射器（BeanNameUrlHandlerMapping，SimpleUrlHandlerMapping）两种
//非注解映射器
<!-- 配置Handler -->
<bean id="itemsController1" name="/queryItems_test.action" class="cn.itcast.ssm.controller.ItemsController1" />
<!-- 配置另外一个Handler -->
<bean id="itemsController2" class="cn.itcast.ssm.controller.ItemsController2" />


<!-- 处理器映射器 将bean的name作为url进行查找 ，需要在配置Handler时指定beanname（就是url） 
所有的映射器都实现 HandlerMapping接口。
-->
<bean
	class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" />
	
<!--简单url映射  -->
<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
	<property name="mappings">
		<props>
			<!-- 对itemsController1进行url映射，url是/queryItems1.action -->
			<prop key="/queryItems1.action">itemsController1</prop>
			<prop key="/queryItems2.action">itemsController1</prop>
			<prop key="/queryItems3.action">itemsController2</prop>
		</props>
	</property>
</bean>
<!-- 处理器适配器 所有处理器适配器都实现 HandlerAdapter接口 -->
<bean
	class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
<!-- 另一个非注解的适配器 -->
<bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"/>

这样两个url映射一个类，也就是访问 queryItems1.action 和 queryItems2.action 都可以访问到 ItemsController1
结论：多个映射器可以并存，前端控制器判断url能让哪个映射器处理就让哪个映射器处理。


1.2:非注解适配器（SimpleControllerHandlerAdapter，HttpRequestHandlerAdapter）
<!-- 处理器适配器 所有处理器适配器都实现 HandlerAdapter接口 -->
<bean
	class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
<!-- 另一个非注解的适配器 -->
<bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"/>
---------------------------------------------
2.1:注解映射器
	<!--注解映射器 -->
	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
	<!--注解适配器 -->
	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>

	--------
	<!-- 使用 mvc:annotation-driven代替上边注解映射器和注解适配器配置
	mvc:annotation-driven默认加载很多的参数绑定方法，
	比如json转换解析器就默认加载了，如果使用mvc:annotation-driven不用配置上边的RequestMappingHandlerMapping和RequestMappingHandlerAdapter
	实际开发时使用mvc:annotation-driven
	 -->
	<!-- <mvc:annotation-driven></mvc:annotation-driven> -->
	--------
2.2:注解适配器
	定义好注解适合器之后，就可以通过注解访问处理器。
	@Controller @RequestMapping 等