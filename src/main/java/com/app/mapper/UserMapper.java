package com.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
