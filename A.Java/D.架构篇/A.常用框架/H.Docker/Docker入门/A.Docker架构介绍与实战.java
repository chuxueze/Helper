1.简介:
2.将用户加入 docker 组中
3.启动 Docker 后台服务














------------------------------------------------------------------
1.简介:

    Docker 是1个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到1个可移植的容器中，
然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。
    
    理解，装着容器与代码的东西。容器可以理解为服务器，例如，tomcat,nigix等。对容器与代码整个进行
打包与部署。


--------------------------------
2.将用户加入 docker 组中
    
    运行 docker 命令时，不用每次都调用 sudo 用户，去输入密码。


--------------------------------
3.启动 Docker 后台服务
    
    sudo systemctl start docker