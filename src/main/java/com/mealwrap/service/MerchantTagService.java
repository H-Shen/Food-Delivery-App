package com.mealwrap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mealwrap.entity.Merchant;
import com.mealwrap.entity.MerchantTag;

import java.util.List;

public interface MerchantTagService extends IService<MerchantTag> {
    List<Merchant> listByTagName(String tagName);
}
