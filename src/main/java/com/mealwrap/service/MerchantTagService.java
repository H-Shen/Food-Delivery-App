package com.mealwrap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mealwrap.entity.MerchantTag;

import java.util.List;
import java.util.Map;

public interface MerchantTagService extends IService<MerchantTag> {
    List<Map<String, Object>> listByTagName(String tagName);
}
