package com.app.controller;

import com.app.common.Result;
import com.app.common.ResultEnum;
import com.app.service.MerchantTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/merchanttag")
@Api(tags = "Merchant Tag Controller")
public class MerchantTagController {

    @Resource
    private MerchantTagService merchantTagService;

    @ApiOperation("Get all merchants with the tag name without merchants' images")
    @GetMapping("/tagname")
    public Result<List<Map<String, Object>>> listByTagName(
            @RequestParam("tagName") @NotNull String tagName) {
        if (tagName == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "tag name is null");
        }
        List<Map<String, Object>> merchants = merchantTagService.listByTagName(tagName);
        if (merchants == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to obtain merchants with the given tag name");
        }
        return Result.success(merchants);
    }
}
