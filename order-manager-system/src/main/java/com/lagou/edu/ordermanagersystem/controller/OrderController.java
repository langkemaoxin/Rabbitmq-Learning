package com.lagou.edu.ordermanagersystem.controller;

import com.lagou.edu.ordermanagersystem.pojo.MyOrder;
import com.lagou.edu.ordermanagersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/list")
    public String getOrderList(Model model){
        List<MyOrder> myOrderList = orderService.getAllOrder();

        model.addAttribute("orderList", myOrderList);

        return "client/index";
    }

}
