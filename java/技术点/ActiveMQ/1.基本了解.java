目录：
1.JMS
2.ActiveMQ初步
3.高级主题（两种经典的消息模式，与Spring整合，集群，监控，配置优化等等）


概述：
	发送者将消息发送给消息服务器，消息服务器将消息存放在若干队列中，在合适的时候再将消息发给接收者。在这种模式下，发送和接收是异步的，发送者无需等待。二者的生命周期未必相同，发送消息的时候，接收者不一定运行，接收消息的时候，发送者也不一定运行。一对多通信，对于一个消息可以有多个接收者。


JSM术语
Provider(MessageProvider)生产者
Consumer(MessageConsumer)消费者
PTP:Point to Point，点对点消息模型
Pub/Sub:Publish/Subscribe,即发布/订阅的消息模型
Queue:队列目标
Topic：主要目标
ConnectionFactory：连接工厂，JMS用它创建连接
Connection：JMS客户端到JMS Provider的连接
Destination：消息的目的地
Session：会话，一个发送或接收消息的线程