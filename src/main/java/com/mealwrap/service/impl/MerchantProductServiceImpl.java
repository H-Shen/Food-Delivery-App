package com.mealwrap.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mealwrap.entity.MerchantProduct;
import com.mealwrap.mapper.MerchantProductMapper;
import com.mealwrap.service.MerchantProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("merchantProductService")
public class MerchantProductServiceImpl extends ServiceImpl<MerchantProductMapper, MerchantProduct> implements MerchantProductService {
    @Resource
    private MerchantProductMapper merchantProductMapper;
}
