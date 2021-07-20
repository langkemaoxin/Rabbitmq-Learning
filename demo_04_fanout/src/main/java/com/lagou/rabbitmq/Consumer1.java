package com.lagou.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //使用这种方式获取一个临时队列
        String queue = channel.queueDeclare().getQueue();

        System.out.println("临时队列名称："+queue);

        //声明一个fanout类型的交换器
        channel.exchangeDeclare("ex.myfan", BuiltinExchangeType.FANOUT,true,false,null);

        //把队列和交换器绑定在一起，接收来自 交换器的消息
        channel.queueBind(queue,"ex.myfan","");

        channel.basicConsume(queue, new DeliverCallback() {
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println(" one " + new String(message.getBody(),"utf-8"));
            }
        }, new CancelCallback() {
            public void handle(String consumerTag) throws IOException {

            }
        });

    }
}
