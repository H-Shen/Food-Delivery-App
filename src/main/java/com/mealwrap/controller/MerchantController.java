package com.mealwrap.controller;

import com.mealwrap.service.MerchantService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/v1/merchant")
@Api(tags = "Merchant Controller")
public class MerchantController {
    @Resource
    private MerchantService merchantService;


}
