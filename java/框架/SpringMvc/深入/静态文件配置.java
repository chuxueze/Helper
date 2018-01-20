静态文件如js,css不用经过前端控制器，需要另外配置
<!-- 静态资源解析
包括 ：js、css、img、..
-->
<mvc:resources location="/js/" mapping="/js/**"/>
<mvc:resources location="/img/" mapping="/img/**"/