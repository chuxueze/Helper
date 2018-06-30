1.反向代理
2.负载均衡
3.HTTP服务器(包含动静分离)
4.正向代理






---------------------------------
1.反向代理

    反向代理（Reverse Proxy）方式是指以代理服务器来接受 internet 上的连接请求，
然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给 internet 
上请求连接的客户端，此时代理服务器对外就表现为1个反向代理服务器。

简单来说:
    就是将你访问的地址指向另1个地址。应用场景，别人访问你的域名地址时，你将
域名代理到你内网中的1台服务器。
如下:
   server {
        listen       80;
        server_name  test.com;

        location / {
        	proxy_pass http://127.0.0.1:8888;
        	proxy_set_header Host $host;
        }
  }
 此配置将 test.com 反射代理到  http://127.0.0.1:8888
 当访问 test.com 时相当于访问  http://127.0.0.1:8888


---------------
2.负载均衡

	负载均衡其意思就是分摊到多个操作单元上进行执行，
例如 Web 服务器、FTP服务器、企业关键应用服务器和其它关键任务服务器等，
从而共同完成工作任务。

    简单而言就是当有2台或以上服务器时，根据规则随机的将请求分发到
指定的服务器上处理，负载均衡配置 一 般都需要同时配置反向代理，
通过反向代理跳转到负载均衡。


此配置对应场景是:
	多个客户端访问 aaa.com 时，会根据规则分配到 localhost:8080 和
localhost:8081 两个服务去访问，分担服务器压力。
	upstream test {
        server localhost:8080;
        server localhost:8081;
    }
    server {
        listen       81;                                                         
        server_name  aaa.com;                                               
        client_max_body_size 1024M;
 
        location / {
            proxy_pass http://test;
            proxy_set_header Host $host:$server_port;
        }
    }
每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。


weight
此处的 weight 代表权重
客户端访问10次，则9次访问到8080，1次访问8081 
	upstream test {
        server localhost:8080 weight=9;
        server localhost:8081 weight=1;
    }


ip_hash
下1个请求来的时候请求可能分发到另外1个服务器，当我们的程序不是无状态的时候
（采用了 session 保存数据），这时候就有1个很大的很问题了，比如把登录信息保存到
了 session 中，那么跳转到另外1台服务器的时候就需要重新登录了，所以很多时候我们
需要1个客户只访问1个服务器，那么就需要用 iphash 了，iphash的每个请求按访问ip的
hash结果分配，这样每个访客固定访问1个后端服务器，可以解决 session 的问题。
upstream test {
        ip_hash;
        server localhost:8080;
        server localhost:8081;
    }
限制同个 Ip 只访问同1个服务器。



---------------
3.HTTP服务器(包含动静分离)
  Nginx 本身也是1个静态资源的服务器，当只有静态资源的时候，就可以使用 Nginx 来做服务器。


----Nginx 做静态资源服务器:
server {
        listen       80;                                                         
        server_name  localhost;                                               
        client_max_body_size 1024M;
 
 
        location / {
               root   e:wwwroot;
               index  index.html;
           }
    }
  这样如果访问 http://localhost 就会默认访问到E盘wwwroot目录下面的index.html，
如果1个网站只是静态页面的话，那么就可以通过这种方式来实现部署。



----动静分离
    动静分离是让动态网站里的动态网页根据1定规则把不变的资源和经常变的资源区分开来，
动静资源做好了拆分以后，我们就可以根据静态资源的特点将其做缓存操作，这就是网站静态化处理的核心思路

upstream test{  
       server localhost:8080;  
       server localhost:8081;  
    }   
 
    server {  
        listen       80;  
        server_name  localhost;  
 
        location / {  
            root   e:wwwroot;  
            index  index.html;  
        }  
 
        # 所有静态请求都由nginx处理，存放目录为html  
        location ~ .(gif|jpg|jpeg|png|bmp|swf|css|js)$ {  
            root    e:wwwroot;  
        }  
 
        # 所有动态请求都转发给tomcat处理  
        location ~ .(jsp|do)$ {  
            proxy_pass  http://test;  
        }  
 
        error_page   500 502 503 504  /50x.html;  
        location = /50x.html {  
            root   e:wwwroot;  
        }  
    }
    这样我们就可以把 HTML 以及图片和 css 以及 js 放到 wwwroot 目录下，
而 tomcat 只负责处理 jsp 和请求，例如当我们后缀为 gif 的时候，Nginx 默认会从 wwwroot 
获取到当前请求的动态图文件返回，当然这里的静态文件跟 Nginx 是同1台服务器，我们也可以在
另外1台服务器，然后通过反向代理和负载均衡配置过去就好了，只要搞清楚了最基本的流程，
很多配置就很简单了，另外 localtion 后面其实是1个正则表达式，所以非常灵活。

---------------
4.正向代理

   正向代理，意思是1个位于客户端和原始服务器 (origin server) 之间的服务器，为了从原始服务器
取得内容，客户端向代理发送1个请求并指定目标 (原始服务器)，然后代理向原始服务器转交请求并将获
得的内容返回给客户端。客户端才能使用正向代理。当你需要把你的服务器作为代理服务器的时候，可以用
Nginx 来实现正向代理。