1.为什么要对消息中间件群集
2.集群方式



----------------------------------------------------
1.为什么要对消息中间件群集

实现高可用，以排除单点故障引起的服务中断
实现负载均衡，以提升效率为更多客户提供服务。


2.集群方式

客户端集群:让多个消费者消费同一个队列
Broker clusters:多个Broker之间同步消息
Master Slave:实现高可用（主从方式）






客户端配置:
ActiveMQ失效转移(failover)
  允许当其中一台消息服务器宕机时，客户端在传输层上重新连接到其他消息服务器。
  <property name="brokerURL" value="tcp://127.0.0.1:61616"/>
  brokerURL 配置 
  语法:failover:(url1,...,urlN)?stransportOptions
  public static final url = "failover:(tcp://127.0.0.1:61616,tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";
stransportOptions参数说明:
*randomize:默认为true，表示在URI列表中选择URI连接时是否采用随机策略
*initialReconnectDelay:默认为10，单位毫秒，表示第一次尝试重连之间等待的时间
*maxReconnectDalay:默认为30000，单位毫秒，最长重连的时间间隔