package com.lagou.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("queue.wq",true,false,false,null);

        channel.basicConsume("queue.wq", new DeliverCallback() {
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println(new String(message.getBody(),"utf-8"));
            }
        }, new CancelCallback() {
            public void handle(String consumerTag) throws IOException {

            }
        });

    }
}
