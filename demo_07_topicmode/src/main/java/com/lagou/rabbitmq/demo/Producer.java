package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;

public class Producer {

    //定义日志级别
    private static final String[] LOG_LEVEL={"info","error","warn"};

    //定义日志的确
    private static final String[] LOG_AREA={"beijing","shanghai","shenzhen"};

    //定义日志的模块
    private static final String[] LOG_BIZ={"edu-online","biz-online","emp-online"};


    private static final Random RANDOM=new Random();

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.exchangeDeclare("ex.topic","topic",true,false,null);


        String area,biz,level;
        String routingKey,message;
        for (int i = 0; i < 100; i++) {
            level=LOG_LEVEL[RANDOM.nextInt(LOG_LEVEL.length)];
            biz=LOG_BIZ[RANDOM.nextInt(LOG_BIZ.length)];
            area=LOG_AREA[RANDOM.nextInt(LOG_AREA.length)];

            //BEIJING.EDU-ONLINE.INFO
            routingKey=area+"."+biz+"."+level;
            message = "LOG: [" + level + "] :这是 [" + area + "] 地区 [" + biz + "] 服务器发来的消息，MSG_SEQ = " + i;

            //发送消息的时候，routingKey为不同的组合
            channel.basicPublish("ex.topic",routingKey,null,message.getBytes());
        }

        channel.close();
        connection.close();

    }
}
