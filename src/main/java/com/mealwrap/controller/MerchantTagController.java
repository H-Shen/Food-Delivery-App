package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Merchant;
import com.mealwrap.service.MerchantService;
import com.mealwrap.service.MerchantTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/merchanttag")
@Api(tags = "Merchant Tag Controller")
public class MerchantTagController {
    @Resource
    private MerchantTagService merchantTagService;
    @Resource
    private MerchantService    merchantService;

    @ApiOperation("Get all merchants with the tag name without merchants' images")
    @GetMapping("/tagname")
    public Result<List<Merchant>> listByTagName(
            @RequestParam("tagName") @NotNull String tagName) {
        if (tagName == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "tag name is null");
        }
        List<Merchant> merchantList = merchantTagService.listByTagName(tagName);
        if (merchantList == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to obtain merchants with the given tag name");
        }
        return Result.success(merchantList);
    }

}
