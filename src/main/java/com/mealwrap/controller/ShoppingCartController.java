package com.mealwrap.controller;

import com.mealwrap.service.ShoppingCartService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/shoppingcart")
@Api(tags = "Shopping Cart Controller")
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;
}
