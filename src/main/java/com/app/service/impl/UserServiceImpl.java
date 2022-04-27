package com.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.entity.User;
import com.app.mapper.UserMapper;
import com.app.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
}