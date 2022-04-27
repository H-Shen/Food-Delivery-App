package com.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.entity.Merchant;
import com.app.mapper.MerchantMapper;
import com.app.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {
    @Resource
    private MerchantMapper merchantMapper;
}
