JMS编码规范
1.ConnectionFactory:用于创建连接到消息中间件的连接工厂
2.Connection:代表了应用程序和消息服务器之间的通信链路
3.Destination:指消息发布和接收的地点，包括队列和主题
4.Session:表示一个单线程的上下文，用于发送和接收消息
5.MessageConsumer:由会话创建，用于接收发送到目标的消息
6.MessageProducer:由会话创建，用于发送消息到目标
7.Message:是在消费者和生产者之间传送的对象，消息头，一组消息属性，一个消息体








-----------------------------------------------------------

public class MqModel {
	/**
	 * MQ默认端口
	 */
	public static final String URL = "tcp://localhost:61616";
	/**
	 * 第一个队列 名字
	 */
	public static final String QUEUENAME1 = "testQueue1"; 
	/**
	 * 第一个  主题 名字
	 */
	public static final String TOPICNAME1 = "testTOPIC"; 
}


----------------------------------------------------------------
/**
 * 消息生产者
 * 
 * @author Administrator
 *
 */
public class Producer {
	public static void main(String[] args) throws Exception {
		// 1.建立ConnectionFactory工厂对象，需要填入用户名，密码以及要连接的地址
		// ，均使用默认即可，默认端口为"tcp://localhost:61616"(可从activimq.xml查看)
		ConnectionFactory connectoinFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, MqModel.URL);
		// 2.通过ConnectionFactory工厂对象，创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
		Connection connection = connectoinFactory.createConnection();
		if(connection!=null) {
			connection.start();
		}
		// 3.通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数1为是否启用事务，参数2为签收模式，一般我们设置自动签收。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象。在PTP模式中，Destination被称作Queue即队列;在Pub/Sub模式，Destination被称作Topic即主题。在程序
		// 中可以使用多个Queue和Topic。
		Destination destination = session.createQueue(MqModel.QUEUENAME1);

		/****************************************************************/
		/*Destination destination = session.createTopic(MqModel.TOPICNAME1);*/
		/****************************************************************/
		
		// 5.我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
		MessageProducer messageProducer = session.createProducer(destination);
		// 6.我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode）
		// 设置持久化，则将数据写入数据库中，不设置持久化，则不将数据写入数据库中
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 7.最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProducer的send方法发送数据。同理客户端使用receive方法进行接收数据。最后不要忘记关闭Connection连接。
		// TextMessage mapMessage = session.createTextMessage();...
		for (int i = 0; i < 10; i++) {
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setString("message"+i+":", "第"+(i+1)+"条订单进来了");
			messageProducer.send(mapMessage);
			System.out.println("生产者生产第"+i+"条消息成功"+new Date(mapMessage.getJMSTimestamp()));
		}
		if(connection != null){
			//close之后，会递归地去关闭子节点
			connection.close();
		}
	}
}



------------------------------------------------------------------



/**
 * 消息消费者
 * @author Administrator
 *
 */
public class Receiver {
	public static void main(String[] args) throws Exception {
		// 1.建立ConnectionFactory工厂对象，需要填入用户名，密码以及要连接的地址
		// ，均使用默认即可，默认端口为"tcp://localhost:61616"(可从activimq.xml查看)
		ConnectionFactory connectoinFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, MqModel.URL);
		// 2.通过ConnectionFactory工厂对象，创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
		Connection connection = connectoinFactory.createConnection();
		if(connection!=null) {
			connection.start();
		}
		// 3.通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数1为是否启用事务，参数2为签收模式，一般我们设置自动签收。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象。在PTP模式中，Destination被称作Queue即队列;在Pub/Sub模式，Destination被称作Topic即主题。在程序
		// 中可以使用多个Queue和Topic。
		Destination destination = session.createQueue(MqModel.QUEUENAME1);

		/****************************************************************/
		/*Destination destination = session.createTopic(MqModel.TOPICNAME1);*/
		/****************************************************************/

		// 5.我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		//创建一个监听器
		/*messageConsumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				MapMessage mapMessage = (MapMessage) message;
				try {
					System.out.println("接收消息："+mapMessage.getMapNames());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});*/
		while(true){
			MapMessage mapMessage = (MapMessage) messageConsumer.receive();
			if(mapMessage == null) break;
			System.out.println("接收到"+mapMessage.getJMSMessageID());
			
		}
		if(connection != null){
			//close之后，会递归地去关闭子节点
			connection.close();
		}
	}
}



