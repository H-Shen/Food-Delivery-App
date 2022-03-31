package com.mealwrap.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mealwrap.entity.ShoppingCart;
import com.mealwrap.mapper.ShoppingCartMapper;
import com.mealwrap.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public int removeByMerchantId(Integer userId, Integer merchantId) {
        return shoppingCartMapper.removeByMerchantId(userId, merchantId);
    }

    @Override
    public List<Map<String, Object>> listById(Integer id) {
        return shoppingCartMapper.listById(id);
    }
}
