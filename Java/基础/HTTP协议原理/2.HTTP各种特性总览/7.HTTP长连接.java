1.TCP 连接



--------------------------------
1.TCP 连接
HTTP请求数据时，会先创建一个 TCP 连接。
TCP 连接分长连接和短连接。
现在网站都是保持长连接的。

浏览器F12中，打开 Network-ALl 右键，选择显示 ConnectionId，
可以查看请求是否是同个TCP连接(如果相同则视为同个TCP连接)。

浏览器允许并发连接为6个，并发连接就是指多个请求可以在同个TCP连接上传输。 

Connection: keep-alive
可以说明此连接是长连接。

Network 
最后一个属性
Online 
Fast 3G
Slow 3G
可以调整网速