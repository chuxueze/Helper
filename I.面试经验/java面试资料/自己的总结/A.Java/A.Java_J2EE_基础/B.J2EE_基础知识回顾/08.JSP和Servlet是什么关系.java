

    Servlet 是1个特殊的 Java 程序，它运行于服务器的JVM中，能够依靠服务器的支持向浏览器提供显示内容。
JSP 本质上是 Servlet 的1种简易形式，JSP 会被服务器处理成1个类似于 Servlet 的 Java 程序，可以简化页面内容的生成。

    Servlet 和 JSP 最主要的不同点在于:
Servlet 的应用逻辑是在 Java 文件中，并且完全从表示层中的HTML分离开来。
JSP 的情况是 Java 和 HTML 可以组合成1个扩展名为 .jsp 的文件。

    JSP侧重于视图，Servlet 更侧重于控制逻辑，在MVC架构模式中，JSP适合充当视图（view）而 Servlet 适合充当控制器（controller）