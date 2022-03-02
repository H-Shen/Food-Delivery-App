package com.mealwrap.controller;

import com.mealwrap.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {

    @Resource
    private UserService userService;

}
