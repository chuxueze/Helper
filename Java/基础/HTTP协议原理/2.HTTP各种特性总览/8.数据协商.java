1.概念
2.数据协商分类








------------------------------------------
1.概念
  客户端发送请求给服务端时，客户端会声明请求的声明格式与相关限制是怎样的。
服务端会根据客户端发送的请求信息进行数据的返回。




2.数据协商分类
请求:
Request Headers
Accept: text/plain, */; q=0.01
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3350.0 Safari/537.36

Accept:请求的数据格式
Accept-Encoding:请求的编码方式
Accept-Language:请求的语言编码
User-Agent:浏览器的相关信息



返回:
Response Headers
Content-Type: text/html;charset=utf-8
Content-Encoding: gzip
Content-Language: zh-CN

Content-Type:对应 Accept 
Content-Encoding: 对应 Accept-Encoding
Content-Language: 对应 Accept-Language