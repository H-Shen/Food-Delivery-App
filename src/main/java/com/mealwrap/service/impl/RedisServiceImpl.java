package com.mealwrap.service.impl;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("redisService")
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<List<String>> getKeys() {
        Set<String> keys = stringRedisTemplate.keys("*");
        if (keys == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis:Failed to obtain the key");
        }
        List<String> result = new ArrayList<>(keys);
        return Result.success(result);
    }

    @Override
    public Result<Void> insert(String key, String value) {
        if (key == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键不能为空");
        }
        if (value == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis值不能为空");
        }
        stringRedisTemplate.opsForValue().set(key, value);
        return Result.success("redis键值插入成功");
    }

    @Override
    public Result<Void> notExist(String key) {
        if (key == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键不能为空");
        }
        Boolean result = stringRedisTemplate.hasKey(key);
        if (result == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键查找失败");
        }
        if (result) {
            return Result.error(ResultEnum.NOT_FOUND, "redis键已存在");
        }
        return Result.success("redis键不存在");
    }

    @Override
    public Result<Void> delete(String key) {
        if (key == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键不能为空");
        }
        Boolean found = stringRedisTemplate.hasKey(key);
        if (found == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键查找失败");
        }
        if (!found) {
            return Result.error(ResultEnum.NOT_FOUND, "redis键不存在");
        }
        Boolean result = stringRedisTemplate.delete(key);
        if (result == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "redis键删除失败");
        }
        return Result.success("redis键删除成功");
    }
}

