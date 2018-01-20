原理：
    前端控制器，处理器映射器，处理适配器，视图解析器。

什么是springmvc？
springmvc框架原理（掌握）
	前端控制器、处理器映射器、处理器适配器、视图解析器
springmvc入门程序
	目的：对前端控制器、处理器映射器、处理器适配器、视图解析器学习
	非注解的处理器映射器、处理器适配器
	注解的处理器映射器、处理器适配器（掌握）
springmvc和mybatis整合（掌握）

springmvc注解开发：（掌握）
	常用的注解学习
	参数绑定（简单类型、pojo、集合类型）
	自定义参数绑定（掌握）
springmvc和struts2区别
-------------------------
springMvc:
springMvc是spring框架的一个模块,基于mvc的一个web框架。


BS 系统下的应用
Mvc：
C:Controller
接收用户请求，响应数据,将模型数据填充到request域
M: pojo, dao, service, (action)
逻辑处理结果之后，将数据返回 C
V: jsp
处理数据，装饰数据的查看界面返回用户


SpringMvc:
C:
request请求，发送 Url
前端控制器，接收用户请求，响应数据。
处理器映射器，HandlerMapping (根据url找到对应的处理器)
M:
Handler 处理器（平时叫Controller）
V:
--------------

前端控制器（请求查找 Handler）-->处理器映射器（HandlerMapping）-->处理器（Handler）-->处理器适配器（HandlerAdapter：执行 Handler）
视图解析器（View resolver）-->视图

原理步骤：
第一步：发起请求到前端控制器(DispatcherServlet)
第二步：前端控制器请求 HandlerMapping 查找 Handler
	可以根据xml配置、注解进行查找
第三步：处理器映射器 HandlerMapping 向前端控制器返回 Handler
第四步：前端控制器调用处理器适配器去执行 Handler
第五步：处理器适配器去执行 Handler
第六步：Handler 执行完成给适配器返回 ModelAndView
第七步：处理器适配器向前端控制器返回 ModelAndView
	ModelAndView 是 springmvc 框架的一个底层对象，包括 Model 和 view
第八步：前端控制器请求视图解析器去进行视图解析
	根据逻辑视图名解析成真正的 视图(jsp)
第九步：视图解析器向前端控制器返回View
第十步：前端控制器进行视图渲染
	视图渲染将模型数据(在 ModelAndView 对象中)填充到request域
第十一步：前端控制器向用户响应结果 

----------
组件：
1、前端控制器 DispatcherServlet（不需要程序员开发）
作用接收请求，响应结果，相当于转发器，中央处理器。
有了 DispatcherServlet 减少了其它组件之间的耦合度。

2、处理器映射器 HandlerMapping (不需要程序员开发)
作用：根据请求的 url 查找 Handler


3、处理器适配器 HandlerAdapter
作用：按照特定规则（HandlerAdapter 要求的规则）去执行Handler

4、处理器 Handler(需要程序员开发)
注意：编写 Handler 时按照 HandlerAdapter 的要求去做，这样适配器才可以去正确执行 Handler

5、视图解析器 View resolver (不需要程序员开发)
作用：进行视图解析，根据逻辑视图名解析成真正的视图（view）

6、视图View(需要程序员开发jsp)
View 是一个接口，实现类支持不同的View类型（jsp、freemarker、pdf...）
