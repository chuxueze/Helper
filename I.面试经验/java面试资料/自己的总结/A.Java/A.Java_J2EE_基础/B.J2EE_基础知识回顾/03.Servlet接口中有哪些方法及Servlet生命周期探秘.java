Servlet 接口定义了5个方法，其中前三个方法与 Servlet 生命周期相关：

void init(ServletConfig config) throws ServletException
void service(ServletRequest req, ServletResponse resp) throws ServletException, java.io.IOException
void destory()
java.lang.String getServletInfo()
ServletConfig getServletConfig()


  生命周期:
1:Web 容器加载 Servlet 并将其实例化后，Servlet 生命周期开始，
2:容器运行其 init() 方法进行 Servlet 的初始化；
3:请求到达时调用 Servlet 的 service() 方法，service() 方法会根据需要调用与请求对应的 doGet 或 doPost 等方法；
4:当服务器关闭或项目被卸载时服务器会将 Servlet 实例销毁，此时会调用 Servlet 的 destroy() 方法。

    init 方法和 destory 方法只会执行1次，service 方法客户端每次请求 Servlet 都会执行。Servlet 中有时会用到1些需要初始化与销毁的资源，
因此可以把初始化资源的代码放入 init 方法中，销毁资源的代码放入 destroy 方法中，这样就不需要每次处理客户端的请求都要初始化与销毁资源。