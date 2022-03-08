package com.mealwrap.controller;

import com.mealwrap.service.MerchantTagService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/merchanttag")
@Api(tags = "Merchant Tag Controller")
public class MerchantTagController {
    @Resource
    private MerchantTagService merchantTagService;
}
