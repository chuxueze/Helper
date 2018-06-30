1.预请求






------------------------------------------
1.预请求
  默认允许跨域的请求:GET、HEAD、POST
  默认允许的Content-Type:text/plain、multipart/form-data、application/x-www-form-urlencoded

如果使用的是其他方法，则需要预请求。
其他限制:
1.请求头限制
2.XMLHttpRequestUpload 对象均没有注册任何事件监听器
3.请求中没有使用ReadableStream 对象