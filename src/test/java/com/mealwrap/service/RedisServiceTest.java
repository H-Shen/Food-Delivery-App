package com.mealwrap.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
@Rollback
class RedisServiceTest {

    @Resource
    private RedisService redisService;

}