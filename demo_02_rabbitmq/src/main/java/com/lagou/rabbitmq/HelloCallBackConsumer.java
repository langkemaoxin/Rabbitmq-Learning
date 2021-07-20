package com.lagou.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class HelloCallBackConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //队列可以重复定义，这里防止先定义了消费者，后定义生产者
        channel.queueDeclare("queue.biz",false,false,false,null);

        channel.basicConsume("queue.biz", (String consumerTag, Delivery message)-> {

            System.out.println("您有一条新消息："+new String(message.getBody()));

        }, consumerTag -> {

        });

    }
}
