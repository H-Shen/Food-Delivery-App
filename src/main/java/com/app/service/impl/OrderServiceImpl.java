package com.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.entity.Order;
import com.app.mapper.OrderMapper;
import com.app.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;
}
