1.代理缓存
2.配置









-------------------------------------------------------
1.代理缓存

    代理缓存相当于在服务器端做了缓存操作，跟浏览器不相关，只要有1个操作访问数据之后，之后的相同操作
都会从缓存里取，不管是否同1个浏览器。

对应请求数据时，需要设置请求
'Cache-Control':'max-age=2,s-max-age=20,private'

s-max-age=20
是相对于代理缓存来设置的，如果同时设置 max-age 和 s-max-age，则会首选 s-max-age。
浏览器缓存只使用 max-age

private:不允许代理服务器缓存

proxy_cache_path cache levels=1:2 keys_zone=my_cache:10m

proxy_cache_path cache
相对路径，放在存放 Nginx 的 cache 路径下面。

levels=1:2
是否创建二级文件夹

keys_zone=my_cache:10m
缓存内存空间，分配 10m 的内存。



---------------
2.配置

proxy_cache_path cache levels=1:2 keys_zone=my_cache:10m

server {
        listen       80;                                                         
        server_name  localhost;                                               
 
        location / {
               proxy_cache my_cache;
               proxy_pass http://127.0.0.1:8888;
               proxy_set_header Host $host;
           }
    }