package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Tag;
import com.mealwrap.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Api(tags = "Tag Controller")
public class TagController {

    @Resource
    TagService tagService;

    @ApiOperation("List all tag names")
    @GetMapping("/all")
    public Result<List<Tag>> list() {
        List<Tag> tags = tagService.list();
        if (tags == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(tags);
    }
}
