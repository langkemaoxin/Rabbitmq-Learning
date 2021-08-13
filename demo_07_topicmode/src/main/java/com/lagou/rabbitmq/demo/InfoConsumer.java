package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class InfoConsumer {
    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("ex.topic", BuiltinExchangeType.TOPIC, true, false, null);

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue, "ex.topic", "shenzhen.*.error");

        channel.basicConsume(queue, new DeliverCallback() {
            public void handle(String consumerTag, Delivery message) throws IOException {

                String s = new String(message.getBody(), "UTF-8");
                System.out.println(s);

            }
        }, new CancelCallback() {
            public void handle(String consumerTag) throws IOException {

            }
        });


    }

}
