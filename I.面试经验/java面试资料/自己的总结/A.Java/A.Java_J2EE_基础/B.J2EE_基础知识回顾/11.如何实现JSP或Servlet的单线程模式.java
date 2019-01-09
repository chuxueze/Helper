对于JSP页面，可以通过 page 指令进行设置。 <%@page isThreadSafe=”false”%>

对于 Servlet，可以让自定义的 Servlet 实现 SingleThreadModel 标识接口。

说明:
    如果将 JSP 或 Servlet 设置成单线程工作模式，会导致每个请求创建1个 Servlet 实例，
这种实践将导致严重的性能问题（服务器的内存压力很大，还会导致频繁的垃圾回收），所以通常情况下并不会这么做。