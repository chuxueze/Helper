RESTful 是一个开发理念，是对http的一个很好的解释。
注意：
1、对url进行规范，写RESTful格式的url

非 REST 的url：http://...../queryItems.action?id=001&type=T01
REST 的url风格：http://..../items/001
	特点：url简洁，将参数通过url传到服务端
2、http的方法规范
不管是删除、添加、更新。使用url是一致的，如果进行删除，需要设置http的方法为delete，同理添加。。。

后台controller方法：判断http方法，如果是delete执行删除，如果是post执行添加。

3、对http的contentType规范
请求时指定contentType，要json数据，设置成json格式的type。


步骤：
1.需要在web.xml中配置 RESTful 前端控制器


----------------------
1.需要在web.xml中配置 RESTful 前端控制器
<!-- springmvc前端控制器，rest配置 -->
<servlet>
	<servlet-name>springmvc_rest</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<!-- contextConfigLocation配置springmvc加载的配置文件（配置处理器映射器、适配器等等） 如果不配置contextConfigLocation，默认加载的是/WEB-INF/servlet名称-serlvet.xml（springmvc-servlet.xml） -->
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring/springmvc.xml</param-value>
	</init-param>
</servlet>
<servlet-mapping>
	<servlet-name>springmvc_rest</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
可以和springmvc的前端控制器并存。




RESTful 风格 Url
前端指定 Url 
localhost:8080/springmvc_mybatis1208/items/itemsView/1/2

//查询商品信息，输出json
///itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
//如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
@RequestMapping("/itemsView/{id}/{type}")
public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id,@PathVariable("type") String abc)throws Exception{
	
	//调用service查询商品信息
	ItemsCustom itemsCustom = itemsService.findItemsById(id);
	
	return itemsCustom;
	
}