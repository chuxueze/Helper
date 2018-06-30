package com.imooc.jms.producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.annotation.Resource;
import javax.jms.*;
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    JmsTemplate jmsTemplate;
    //@Resource(name = "queueDestination")//队列模式
    @Resource(name = "topicDestination")//主题模式
    Destination destination;
    public void sendMessage(final String message) {
        //使用JmsTemplate发送消息
        jmsTemplate.send(destination,new MessageCreator(){
            //创建一个消息
            public Message createMessage(Session session) {
                TextMessage textMessage = null;
                try {
                    textMessage = session.createTextMessage(message);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return textMessage;
            }
        });
        System.out.println("发送消息："+ message);
    }
}
