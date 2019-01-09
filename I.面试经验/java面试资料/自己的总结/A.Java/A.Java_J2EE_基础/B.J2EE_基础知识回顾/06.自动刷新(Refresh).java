
    自动刷新不仅可以实现1段时间之后自动跳转到另1个页面，还可以实现1段时间之后自动刷新本页面。
Servlet 中通过 HttpServletResponse 对象设置 Header 属性实现自动刷新例如：

Response.setHeader("Refresh","5;URL=http://localhost:8080/servlet/example.html");

其中5为时间，单位为秒。URL指定就是要跳转的页面（如果设置自己的路径，就会实现每过1秒自动刷新本页面1次）