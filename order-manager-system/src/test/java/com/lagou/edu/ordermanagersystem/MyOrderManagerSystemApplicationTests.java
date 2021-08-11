package com.lagou.edu.ordermanagersystem;

import com.lagou.edu.ordermanagersystem.pojo.MyOrder;
import com.lagou.edu.ordermanagersystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


class MyOrderManagerSystemApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {

        MyOrder myOrder = new MyOrder();
        myOrder.setCreateTime(new Date());
        myOrder.setUpdateTime(new Date());
        myOrder.setMount(BigDecimal.valueOf(100));
        myOrder.setStatus(1);

        orderService.save(myOrder);

        Page<MyOrder> page = orderService.getOrder(0, 1);

        List<MyOrder> content = page.getContent();

        for (MyOrder o : content) {
            System.out.println(o);
        }
    }

}
