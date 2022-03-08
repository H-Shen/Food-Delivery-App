package com.mealwrap.controller;

import com.mealwrap.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Api(tags = "Order Controller")
public class OrderController {
    @Resource
    private OrderService orderService;
}
