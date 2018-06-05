1.浏览器跨域
2.解决方法






-----------------------------
1.浏览器跨域
Cross-Origin Resource Sharing（CORS）跨域资源共享
如下情况:
（主域名不同:123/456，跨域） http://www.123.com/index.html 调用 http://www.456.com/server.php 

（子域名不同:abc/def，跨域） http://abc.123.com/index.html 调用 http://def.123.com/server.php 

（端口不同:8080/8081，跨域） http://www.123.com:8080/index.html 调用 http://www.123.com:8081/server.php 

（协议不同:http/https，跨域）http://www.123.com/index.html 调用 https://www.123.com/server.php 

请注意：localhost和127.0.0.1虽然都指向本机，但也属于跨域。
--------------
2.解决方法
1)JSONP：
使用方式就不赘述了，但是要注意JSONP只支持GET请求，不支持POST请求。
浏览器允许在 
script、link、img
等标签加载内容时允许跨域
<script src=""></script>
在标签里加载链接

2)代理：

例如www.123.com/index.html需要调用www.456.com/server.php，可以写一个接口www.123.com/server.php，
由这个接口在后端去调用www.456.com/server.php并拿到返回值，然后再返回给index.html，这就是一个代理的模式。
相当于绕过了浏览器端，自然就不存在跨域问题。因为服务器端是不存在跨域的说法的。

3)端修改header

header('Access-Control-Allow-Origin:*');//允许所有来源访问

header('Access-Control-Allow-Method:POST,GET');//允许访问的方式
  

  其实服务器请求是通过了，并且将数据返回了。只是浏览器在解析返回数据的时候，
如果是属于跨域请求，且 head 没有设置
    'Access-Control-Allow-Origin':'*'
  则会将数据拦截。

可以只允许某个域名可以跨域
 'Access-Control-Allow-Origin':'http://www.baidu.com'