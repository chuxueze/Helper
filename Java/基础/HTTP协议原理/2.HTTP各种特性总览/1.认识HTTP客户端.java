1.常见的浏览器
2.
linux系统下
curl -v www.imooc.com
curl可以登录网址

命令可以查看网站信息


* Rebuilt URL to: www.imooc.com/
* timeout on name lookup is not supported
*   Trying 117.121.101.41...
* TCP_NODELAY set
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to www.imooc.com (117.121.101.41) port 80 (#0)
> GET / HTTP/1.1
> Host: www.imooc.com
> User-Agent: curl/7.54.1
> Accept: */*
>
< HTTP/1.1 301 Moved Permanently
< Server: nginx
< Date: Tue, 05 Jun 2018 14:20:54 GMT
< Content-Type: text/html
< Content-Length: 178
< Connection: keep-alive
< Location: https://www.imooc.com
<
{ [178 bytes data]
100   178  100   178    0     0   1618      0 --:--:-- --:--:-- --:--:--  1893<html>
<head><title>301 Moved Permanently</title></head>
<body bgcolor="white">
<center><h1>301 Moved Permanently</h1></center>
<hr><center>nginx</center>
</body>
</html>

* Connection #0 to host www.imooc.com left intact
