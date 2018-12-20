1.JSP 有9个内置对象:
2.Request 对象的主要方法有哪些:
3.request.getAttribute() 和 request.getParameter()有何区别:
4.讲解JSP中的四种作用域:

------------------------------------------------------------------------------------------------------------
1.JSP 有9个内置对象:

request:封装客户端的请求，其中包含来自 GET 或 POST 请求的参数；

response:封装服务器对客户端的响应；

pageContext:通过该对象可以获取其他对象；

session:封装用户会话的对象；

application:封装服务器运行环境的对象；

out:输出服务器响应的输出流对象；

config:Web 应用的配置对象；

page:JSP页面本身（相当于 Java 程序中的 this）；

exception:封装页面抛出异常的对象。


------------------------------------------------------
2.Request 对象的主要方法有哪些:

setAttribute(String name,Object):设置名字为 name 的 request 的参数值
getAttribute(String name):返回由 name 指定的属性值
getAttributeNames():返回 request 对象所有属性的名字集合，结果是1个枚举的实例
getCookies():返回客户端的所有 Cookie 对象，结果是1个 Cookie 数组
getCharacterEncoding() :返回请求中的字符编码方式 = getContentLength() :返回请求的 Body 的长度
getHeader(String name) :获得HTTP协议定义的文件头信息
getHeaders(String name) :返回指定名字的 request Header 的所有值，结果是1个枚举的实例
getHeaderNames() :返回所以 request Header 的名字，结果是1个枚举的实例
getInputStream() :返回请求的输入流，用于获得请求中的数据
getMethod() :获得客户端向服务器端传送数据的方法
getParameter(String name) :获得客户端传送给服务器端的有 name 指定的参数值
getParameterNames() :获得客户端传送给服务器端的所有参数的名字，结果是1个枚举的实例
getParameterValues(String name):获得有 name 指定的参数的所有值
getProtocol():获取客户端向服务器端传送数据所依据的协议名称
getQueryString() :获得查询字符串
getRequestURI() :获取发出请求字符串的客户端地址
getRemoteAddr():获取客户端的 IP 地址
getRemoteHost() :获取客户端的名字
getSession([Boolean create]) :返回和请求相关 Session
getServerName() :获取服务器的名字
getServletPath():获取客户端所请求的脚本文件的路径
getServerPort():获取服务器的端口号
removeAttribute(String name):删除请求中的1个属性



------------------------------------------------------
3.request.getAttribute() 和 request.getParameter()有何区别:

从获取方向来看：

getParameter() 是获取 POST/GET 传递的参数值；

getAttribute() 是获取对象容器中的数据值；

-------------------------
从用途来看：

getParameter 用于客户端重定向时，即点击了链接或提交按扭时传值用，即用于在用表单或 url 重定向传值时接收数据用。

getAttribute 用于服务器端重定向时，即在 sevlet 中使用了 forward 函数,或 struts 中使用了 mapping.findForward。 getAttribute 只能收到程序用 setAttribute 传过来的值。

    另外，可以用 setAttribute,getAttribute 发送接收对象.而 getParameter 显然只能传字符串。 
setAttribute 是应用服务器把这个对象放在该页面所对应的1块内存中去，当你的页面服务器重定向到另1个页面时，应用服务器会把这块内存拷贝另1个页面所对应的内存中。
这样 getAttribute就 能取得你所设下的值，当然这种方法可以传对象。session也1样，只是对象在内存中的生命周期不1样而已。
getParameter 只是应用服务器在分析你送上来的 request 页面的文本时，取得你设在表单或 url 重定向时的值。

-------------------------
总结：

getParameter 返回的是 String,用于读取提交的表单中的值;（获取之后会根据实际需要转换为自己需要的相应类型，比如整型，日期类型啊等等）

getAttribute 返回的是 Object，需进行转换,可用 setAttribute 设置成任意对象，使用很灵活，可随时用

------------------------------------------------------
4.讲解JSP中的四种作用域:

JSP 中的四种作用域包括 page、request、session和application，具体来说：

page:代表与1个页面相关的对象和属性。
request:代表与 Web 客户机发出的1个请求相关的对象和属性。一个请求可能跨越多个页面，涉及多个 Web 组件；需要在页面显示的临时数据可以置于此作用域。
session:代表与某个用户与服务器建立的1次会话相关的对象和属性。跟某个用户相关的数据应该放在用户自己的 session 中。
application:代表与整个 Web 应用程序相关的对象和属性，它实质上是跨越整个 Web 应用程序，包括多个页面、请求和会话的1个全局作用域