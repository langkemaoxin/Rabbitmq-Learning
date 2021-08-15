package com.lagou.rabbit.demo.controller;

import com.lagou.rabbit.demo.enums.OrderStatus;
import com.lagou.rabbit.demo.po.Order;
import com.lagou.rabbit.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("save/{courseId}")
    public String saveOrder(@PathVariable("courseId") Integer courseId,Model model) throws UnsupportedEncodingException {

        String orderId = UUID.randomUUID().toString();
        Order order = new Order();
        order.setOrderNo(orderId);
        order.setStatus(OrderStatus.CREATE.getCode());
        order.setCourseId(courseId);
        orderService.saveOrder(order);

        model.addAttribute("order",order);

        return "order/orderDetail";
    }

    @GetMapping("list")
    public String orderList(Model model) {
        List<Order> orders = orderService.listOrder();

        model.addAttribute("orders", orders);

        return "order/orderList";
    }


}
