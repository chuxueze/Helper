    
    JSP是1种 Servlet，但是与 HttpServlet 的工作方式不太1样。HttpServlet 是先由源代码编译为 class 文件后部署到服务器下，为先编译后部署。
而JSP 则是先部署后编译。JSP 会在客户端第1次请求 JSP 文件时被编译为 HttpJspPage 类（接口 Servlet 的1个子类）。该类会被服务器临时存放在服务器工作目录里面。

    下面通过实例给大家介绍。 工程 JspLoginDemo 下有1个名为 login.jsp 的 Jsp 文件，把工程第1次部署到服务器上后访问这个 Jsp 文件，我们发现这个目录下多了下图这两个东东。 
.class 文件便是 JSP 对应的 Servlet。编译完毕后再运行 class 文件来响应客户端请求。以后客户端访问 login.jsp 的时候，Tomcat 将不再重新编译JSP文件，而是直接调用 class 文件来响应客户端请求。 
JSP工作原理 由于 JSP 只会在客户端第1次请求的时候被编译 ，因此第1次请求JSP时会感觉比较慢，之后就会感觉快很多。如果把服务器保存的 class 文件删除，服务器也会重新编译JSP。
 
    开发 Web 程序时经常需要修改 JSP。Tomcat 能够自动检测到 JSP 程序的改动。如果检测到JSP源代码发生了改动。Tomcat 会在下次客户端请求JSP时重新编译 JSP，而不需要重启 Tomcat。
这种自动检测功能是默认开启的，检测改动会消耗少量的时间，在部署 Web 应用的时候可以在 web.xml 中将它关掉。