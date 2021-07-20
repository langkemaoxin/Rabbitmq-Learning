package com.lagou.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("queue.default.ex",false,false,false,null);

        //这里使用主动拉的方式进行获取
        GetResponse getResponse = channel.basicGet("queue.default.ex", false);

        System.out.println(new String(getResponse.getBody()));

        channel.close();

        connection.close();

    }
}
