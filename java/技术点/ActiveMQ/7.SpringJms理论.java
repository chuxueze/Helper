使用Spring集成JMS连接ActiveMQ
1.ConnectionFactory:用于管理连接的连接工厂
2.JmsTemplate:用于发送和接收消息的模板类
3.MessageListerner:消息监听器





------------------------------------
1.ConnectionFactory:用于管理连接的连接工厂
1.1:一个Spring为我们提供的连接池
1.2:JmsTemplate每次发消息都会重新创建连接，会话和productor
(每次都会创建新的连接，都会消耗性能)
1.3:Spring中提供了SingleConnectionFactory和CachingConnectionFactory
SingleConnectionFactory:单例，只会创建一个ConnectionFactory
CachingConnectionFactory:继承自SingleConnectionFactory，提供缓存功能





2.JmsTemplate:用于发送和接收消息的模板类
2.1:是Spring提供的，只需向Spring窗口内注册这个类就可以使用JmsTemplate方便的操作jms
2.2:JmsTemplate类是线程安全的，可以在整个应用范围使用 






3.MessageListerner:消息监听器
3.1:实现一个onMessage方法，该方法只接收一个Message参数