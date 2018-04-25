1.代码
2.pom文件引入
3.resources文件







---------------------------------------------------------------------------------
1.代码

ProducerService.java

import javax.jms.Destination;
public interface ProducerService {
    void sendMessage(String message);
}

--------------

ProducerServiceImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.annotation.Resource;
import javax.jms.*;
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    JmsTemplate jmsTemplate;
    @Resource(name = "queueDestination")
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

----------------

AppProducer.java

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.jms.*;

public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            service.sendMessage("----第"+i+"条消息----");
        }
        context.close();
    }
}


--------------------------------------------------

2.pom文件引入

pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.imooc.jms</groupId>
    <artifactId>jms-spring</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <spring.version>4.2.5.RELEASE</spring.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jms</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.activemq</groupId>
            <artifactId>activemq-core</artifactId>
            <version>5.7.0</version>
            <exclusions>
                <exclusion>
                    <artifactId>spring-context</artifactId>
                    <groupId>org.springframework</groupId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
</project>

--------------------------------------------------

3.resources文件

producer.xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config/>
    <!--ActiveMQ为我们提供的ConncetionFactory-->
    <bean id="targetConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://127.0.0.1:61616"/>
    </bean>
    <!--spring jms 为我们提供连接池-->
    <bean id="connectionFactory" class="org.springframework.jms.connection.SingleConnectionFactory">
        <property name="targetConnectionFactory" ref="targetConnectionFactory"/>
    </bean>
    <!--一个队列目的地，点对点的-->
    <bean id="queueDestination" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg value="queue"/>
    </bean>
    <!--配置JmsTemplate，用于发送消息-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactory"/>
    </bean>

    <bean id="ProducerService" class="com.imooc.jms.producer.ProducerServiceImpl"></bean>
</beans>


