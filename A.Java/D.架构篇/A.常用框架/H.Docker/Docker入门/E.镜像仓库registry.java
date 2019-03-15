1.步骤:








----------------------------------------------------------------------
1.步骤:

   类似于 github 仓库。



1.1:查找 whalesay 相关镜像
sudo docker search whalesay 

1.2:从仓库中拉取 whalesay
sudo docker pull docker/whalesay

1.3:运行 docker 镜像
sudo docker run docker/whalesay cowsay docker is good!

1.4:设置镜像标签
sudo docker tag docker/whalesay zhc/whalesay

1.5:将镜像 push 到仓库
sudo docker push zhc/whalesay

注意:
push 之前需要登录 docker 
docker login