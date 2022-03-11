package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Order;
import com.mealwrap.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "Order Controller")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("List all orders")
    @GetMapping("/all")
    public Result<List<Order>> list() {
        List<Order> orders = orderService.list();
        ;
        if (orders == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(orders);
    }
}
