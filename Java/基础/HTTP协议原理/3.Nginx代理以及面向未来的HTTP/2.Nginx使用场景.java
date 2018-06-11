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