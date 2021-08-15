package com.lagou.rabbit.demo.listener;

import com.lagou.rabbit.demo.po.Order;
import com.lagou.rabbit.demo.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 专门用于取消订单的，取消订单的做法一般都是直接调用方法进行
 *
 * 但是这里使用的是消息队列，进行取消，付款超时
 */
@Component
public class CancelOrderListener {

    @Autowired
    private OrderService orderService;

    /**
     * 从 队列中拿出订单号，然后取消订单
     *
     * 这个队列是死信队列
     * @param orderNo
     * @param channel
     * @param deliveryTag 每个消息的的tag都是唯一的
     */
    @RabbitListener(queues = "q.dlx")
    public void onMessage(@Payload String orderNo,
                          Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        System.out.println("付款已超时，正在取消订单");

        Order order = new Order();
        order.setOrderNo(orderNo);

        orderService.cancelOrder(order);
        channel.basicAck(deliveryTag,false);

    }
}
