package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        //定义一个持久化队列
        channel.queueDeclare("queue.persistant", true, false, false, null);

        //定义一个持久化交换机
        channel.exchangeDeclare("ex.persistant", BuiltinExchangeType.DIRECT, true);

        channel.queueBind("queue.persistant", "ex.persistant", "key.persistant");

        //创建持久化的属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .build();

        channel.basicPublish("ex.persistant",
                "key.persistant",
                properties,
                "hello wolrd".getBytes());


        channel.close();
        connection.close();


    }
}
