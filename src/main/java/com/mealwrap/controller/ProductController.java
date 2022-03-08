package com.mealwrap.controller;

import com.mealwrap.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
@Api(tags = "Product Controller")
public class ProductController {
    @Resource
    private ProductService productService;
}
