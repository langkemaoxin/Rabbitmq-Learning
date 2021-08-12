package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class BeijingConsumer {
    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.200/2%f");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明交换机，防止不存在
        channel.exchangeDeclare("ex.topic", "topic", true, false, null);

        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        //把队列和交换机绑定了在一起，绑定key的字符串模式为： beijing.#
        channel.queueBind(queue, "ex.topic", "beijing.#");

        channel.basicConsume(queue, new DeliverCallback() {
                    public void handle(String consumerTag, Delivery message) throws IOException {

                        System.out.println(new String(message.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    public void handle(String consumerTag) throws IOException {

                    }
                }
        );


    }
}
