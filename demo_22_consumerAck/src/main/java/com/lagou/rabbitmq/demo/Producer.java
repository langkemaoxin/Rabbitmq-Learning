package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();


        channel.queueDeclare("queue.ca", false, false, false, null);
        channel.exchangeDeclare("ex.ca", "direct", false, false, null);
        channel.queueBind("queue.ca", "ex.ca", "key.ca");

        for (int i = 0; i < 5; i++) {
            channel.basicPublish("ex.ca", "key.ca", null, ("hello-" + i).getBytes());
        }

        channel.close();
        connection.close();

    }
}
