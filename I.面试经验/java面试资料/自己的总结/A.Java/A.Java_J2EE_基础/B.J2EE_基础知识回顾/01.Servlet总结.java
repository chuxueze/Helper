1:Servlet 总结:





-------------------------------------------------------------------------------------------
1:Servlet 总结:
    在 Java Web 程序中，Servlet 主要负责接收用户请求 HttpServletRequest,在doGet(),doPost()中做相应的处理，
并将回应 HttpServletResponse 反馈给用户。Servlet 可以设置初始化参数，供 Servlet 内部使用。
1个 Servlet 类只会有1个实例，在它初始化时调用 init()方法，销毁时调用 destroy()方法。
    Servlet 需要在 web.xml 中配置（MyEclipse 中创建 Servlet 会自动配置），一个 Servlet 可以设置多个URL访问。
Servlet 不是线程安全，因此要谨慎使用类变量


