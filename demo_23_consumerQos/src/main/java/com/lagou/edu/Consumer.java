package com.lagou.edu;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("queue.qos", false, false, false, null);

        //限流10个消息，多余10个消息则进行限流
        channel.basicQos(10,false);

        channel.basicConsume("queue.qos",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(new String(body)+" envelope.getDeliveryTag()=" + envelope.getDeliveryTag() );

                //批量确认
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}
