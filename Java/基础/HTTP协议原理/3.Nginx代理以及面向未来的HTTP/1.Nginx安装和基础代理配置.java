1.Nginx 概念:
2.启动步骤:
3.配置
4.例子








----------------------------------------------
1.Nginx 概念:
Nginx (engine x) 是一个高性能的 HTTP 和反向代理服务器，也是一个 IMAP/POP3/SMTP服务器。

HTTP 请求 Nginx，Nginx 进行转发，发起一个 HTTP 请求到后台。

----------------
2.启动步骤:
进入 nginx 安装目录,cmd 执行
./nginx.exe

打开浏览器，输入 localhost,可以访问证明已经启动成功

----------------
3.配置
D:\Nginx\nginx-1.15.0\conf
nginx.conf

在 http 的大括号里增加配置
include       servers/*.conf;
此配置可以使servers目录下的其他.conf文件生效

----------------
4.例子

新建servers文件夹
再新建test.conf文件，添加代理配置
  server {
        listen       80;
        server_name  test.com;

        location / {
        	proxy_pass http://127.0.0.1:8888;
        	proxy_set_header Host $host;
        }
  }
则下面两个地址都可以访问

http://127.0.0.1:8888/js/a/login
http://test.com/js/a/login