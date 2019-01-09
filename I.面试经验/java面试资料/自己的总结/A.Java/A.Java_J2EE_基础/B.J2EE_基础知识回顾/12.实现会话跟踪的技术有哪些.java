1.使用 Cookie:
2.URL 重写:
3.隐藏的表单域:
4.HttpSession:






-----------------------------------------------------------------------------------------------------------------------
1.使用 Cookie:

向客户端发送 Cookie

Cookie c = new Cookie("name","value"); //创建Cookie 
c.setMaxAge(60*60*24); //设置最大时效，此处设置的最大时效为一天
response.addCookie(c); //把Cookie放入到HTTP响应中

从客户端读取 Cookie

String name ="name"; 
Cookie[]cookies =request.getCookies(); 
if(cookies !=null){ 
   for(int i= 0;i<cookies.length;i++){ 
    Cookie cookie =cookies[i]; 
    if(name.equals(cookis.getName())) 
    //something is here. 
    //you can get the value 
    cookie.getValue(); 
       
   }
 }
优点: 数据可以持久保存，不需要服务器资源，简单，基于文本的 Key-Value

缺点: 大小受到限制，用户可以禁用 Cookie 功能，由于保存在本地，有1定的安全风险。

----------------------------------------------------------------
2.URL 重写:

在URL中添加用户会话的信息作为请求的参数，或者将唯1的会话ID添加到URL结尾以标识1个会话。

优点： 在 Cookie 被禁用的时候依然可以使用

缺点： 必须对网站的URL进行编码，所有页面必须动态生成，不能用预先记录下来的URL进行访问。


----------------------------------------------------------------
3.隐藏的表单域:

<input type="hidden" name ="session" value="..."/>
优点： Cookie 被禁时可以使用

缺点： 所有页面必须是表单提交之后的结果。


----------------------------------------------------------------
4.HttpSession:
    在所有会话跟踪技术中，HttpSession 对象是最强大也是功能最多的。
当1个用户第1次访问某个网站时会自动创建 HttpSession，每个用户可以访问他自己的 HttpSession。
可以通过 HttpServletRequest 对象的 getSession 方法获得 HttpSession，通过 HttpSession 的 setAttribute 方法可以将1个值放在 HttpSession 中，
通过调用 HttpSession 对象的 getAttribute 方法，同时传入属性名就可以获取保存在 HttpSession 中的对象。
    
    与上面三种方式不同的是，HttpSession 放在服务器的内存中，因此不要将过大的对象放在里面，
即使目前的 Servlet 容器可以在内存将满时将 HttpSession 中的对象移到其他存储设备中，但是这样势必影响性能。
添加到 HttpSession 中的值可以是任意 Java 对象，这个对象最好实现了 Serializable 接口，这样 Servlet 容器在必要的时候可以将其序列化到文件中，
否则在序列化时就会出现异常。