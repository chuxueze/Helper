缓存验证
1.Last-Modified
2.Etag








---------------------------------------------
1.Last-Modified
上次修改时间
    配合 If-Modified-Since 或者 If-Unmodified-Since 使用

    浏览器每次请求会带上 Last-Modified 到服务器后台，如果检查到与服务器
Last-Modified 时间一致，证明数据没有修改，则取浏览器缓存，反之相反。


2.Etag
数据签名 
    配合 If-Match 或者 If-Non-Match 使用，对比资源签名是否匹配。

    内容会对应一个唯一的签名，只要内容有修改，则签名会改变。对资源内容进行 Hash 计算，
得到唯一值。