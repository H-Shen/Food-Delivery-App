package com.mealwrap.controller;

import com.mealwrap.service.MerchantProductService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/merchantproduct")
@Api(tags = "Merchant Product Controller")
public class MerchantProductController {
    @Resource
    private MerchantProductService merchantProductService;
}
