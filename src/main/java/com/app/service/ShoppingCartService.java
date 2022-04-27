package com.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.app.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService extends IService<ShoppingCart> {

    int removeByMerchantId(Integer userId, Integer merchantId);

    List<Map<String, Object>> listById(Integer id);
}
