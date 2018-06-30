1.Cache-Control





-------------------------------
1.Cache-Control
可缓存性
Cache-Control: private
Cache-Control: max-age=0

public
HTTP请求经过的任何地方都可以进行缓存。

private
只有发起的浏览器才能进行缓存

no-cache
不可缓存

max-age=<seconds>
过期时间，当过期之后，浏览器才会重新请求，取得内容

s-maxage=<seconds>
代理服务器优先读取此设置

max-stale=<seconds>