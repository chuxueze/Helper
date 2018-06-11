1.CSP:内容安全策略
2.作用
3.限制方式





----------------------------------
1.CSP:内容安全策略
Content-Security-Policy
为了我们的网站更加安全


------------
2.作用
1)限制资源获取
2)报告资源获取越权



------------
3.限制方式
default-src 限制全局
response.setHeader("Content-Security-Policy", "default-src http: https:");
限制在 HTML 中直接嵌套 JS 脚本，可以防止类似 Xss 攻击。
此设置只限制直接将 js 代码写在 HTML 中的情况，如果 js 代码是引入的方式，是不受影响的。 

response.setHeader("Content-Security-Policy", "default-src \'self\' https://www.baidu.com/");
只允许引用同域名的外链 JS 代码。

具体 API 可以搜索查看 "MDN CSP" 
制定资源类型
如:
connect-src
img-src
font-src
frame-src
media-src
script-src
manifest-src
style-src