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

    // get 给我一个tagname 返回所有商家除了图片的外的字段 这些商家包含这个tag

    //
}
