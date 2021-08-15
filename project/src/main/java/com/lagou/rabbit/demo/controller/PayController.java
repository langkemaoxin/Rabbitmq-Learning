package com.lagou.rabbit.demo.controller;

import com.lagou.rabbit.demo.po.Order;
import com.lagou.rabbit.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/pay/{orderNo}")
    public String payOrder(@PathVariable("orderNo") String orderNo){
        Order order = new Order();
        order.setOrderNo(orderNo);

        orderService.saveOrder(order);

        return "redirect:/pay/pay_success/"+orderNo;
    }

}
