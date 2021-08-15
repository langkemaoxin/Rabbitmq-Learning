package com.lagou.rabbit.demo.listener;

import com.lagou.rabbit.demo.service.CourseService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StoreDeductionListener {

    @Autowired
    private CourseService courseService;

    @RabbitListener(queues = "q.biz")
    public void onMessage(@Payload String courseIdStr,
                          Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        final boolean updateStore = courseService.updateStore(Integer.parseInt(courseIdStr), 1L);

        if (updateStore) {

            channel.basicAck(deliveryTag, false);

        } else {
            channel.basicReject(deliveryTag, true);
        }

    }

}
