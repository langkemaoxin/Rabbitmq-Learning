package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("queue.ca", false, false, false, null);

        //使用推送模式
        //声明队列名
        //是否自动确认
        //消费tag有什么用？
        //处理函数
        channel.basicConsume("queue.ca", false, "myConsumer", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println(new String(body));


                /**
                 * 主动的确认消息 ==> 告知broker 消息已经消费
                 */
                //channel.basicGet("queue.ca",true);

                //绝收一条消息，并且，放回到消息队列中
                //这就会导致一个结果，就是会不停的消费消息，最后导致消费速度下降
                //channel.basicReject(envelope.getDeliveryTag(),true);

            }
        });


    }
}
