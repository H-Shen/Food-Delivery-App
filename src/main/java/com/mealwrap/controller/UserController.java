package com.mealwrap.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mealwrap.common.JwtUtils;
import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.User;
import com.mealwrap.service.RedisService;
import com.mealwrap.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    @ApiOperation("Login with phone and password and return a token")
    @PostMapping("/login")
    public Result<String> login(@RequestBody HashMap<String, Object> args) {
        if (args == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "args is null");
        }
        if (!args.containsKey("phone")) {
            return Result.error(ResultEnum.BAD_REQUEST, "arg:phone cannot be found");
        }
        if (args.get("phone") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "arg:phone is null");
        }
        if (!args.containsKey("password")) {
            return Result.error(ResultEnum.BAD_REQUEST, "arg:password cannot be found");
        }
        if (args.get("password") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "arg:password is null");
        }
        String phone = (String) args.get("phone");
        User   user  = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
        if (user == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "phone cannot be found");
        }
        String rawPassword = (String) args.get("password");
        if (!new BCryptPasswordEncoder().matches(rawPassword, user.getPassword())) {
            return Result.error(ResultEnum.BAD_REQUEST, "password not matched");
        }
        // generate the token by the user ID
        String token;
        try {
            token = JwtUtils.getToken(user.getId());
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to generate the token: " + e.getMessage());
        }
        if (token == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to generate the token");
        }
        // insert the token to redis
        Result<Void> insertToken = redisService.insert(token, "");
        if (!insertToken.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to insert the token to redis");
        }
        return Result.success(token, "user successfully logged in");
    }

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
