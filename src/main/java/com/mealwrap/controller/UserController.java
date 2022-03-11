package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.User;
import com.mealwrap.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("List all users")
    @GetMapping("/all")
    public Result<List<User>> list() {
        List<User> users = userService.list();
        if (users == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(users);
    }
}
