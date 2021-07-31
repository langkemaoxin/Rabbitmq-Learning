package com.lagou.edu;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception  {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();


        channel.queueDeclare("queue.qos", false, false, false, null);
        channel.exchangeDeclare("ex.qos", "direct", false, false, null);
        channel.queueBind("queue.qos", "ex.qos", "key.qos");

        for (int i = 0; i < 5000; i++) {
            channel.basicPublish("ex.qos", "key.qos", null, ("hello-" + i).getBytes());
        }

        channel.close();
        connection.close();
    }
}
