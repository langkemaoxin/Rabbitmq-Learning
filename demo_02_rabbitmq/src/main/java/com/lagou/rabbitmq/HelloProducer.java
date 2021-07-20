package com.lagou.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HelloProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.192.129");

        factory.setVirtualHost("/");

        factory.setUsername("root");

        factory.setPassword("123456");

        factory.setPort(5672);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("queue.biz",false,false,false,null);

        channel.exchangeDeclare("exchange.biz", BuiltinExchangeType.DIRECT,false,false,null);

        //把交换机和队列进行绑定映射
        channel.queueBind("queue.biz","exchange.biz","hello.world");

        //往指定的交换机，路由键，发送消息
        channel.basicPublish("exchange.biz","hello.world",null,"hello world 2".getBytes());


        channel.close();

        connection.close();

    }
}
