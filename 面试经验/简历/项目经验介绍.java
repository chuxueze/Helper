项目经验介绍:
1.功能
2.架构
3.解决了什么问题
4.部署
5.投入使用情况




采用

技术层面:
1.SpringMVC+Mybatis，用Maven管理。
2.对浏览器跨域做了支持，通过在拦截器定义 Response 的返回，设置允许跨域访问。
3.用 Nginx 做了反向代理。对网站进行动静分离。
4.对菜单导航栏热点数据做了 Redis 缓存，采用主从备份，一主两从
5.数据同步采用定时器 SpringQuartz


功能层面:
主要包含什么模块的功能