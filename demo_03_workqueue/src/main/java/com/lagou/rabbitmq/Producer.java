package com.lagou.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("queue.wq",true,false,false,null);

        channel.exchangeDeclare("ex.wq", BuiltinExchangeType.DIRECT,true,false,null);

        //channel.exchangeBind("ex.wq","queue.wq","key.wq");

        //使用QueueBind 指定队列和交换机，通过key.wq 进行绑定
        channel.queueBind("queue.wq","ex.wq","key.wq");

       // channel.exchangeBind("ex.wq", "queue.wq", "key.wq");

        for (int i = 0; i < 15; i++) {
            channel.basicPublish("ex.wq","key.wq",null,("产生的数据"+i).getBytes("utf-8"));
        }

        channel.close();

        connection.close();
    }
}
