package com.mealwrap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mealwrap.entity.Merchant;
import com.mealwrap.entity.MerchantTag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantTagMapper extends BaseMapper<MerchantTag> {
    // List all merchants with the tag name without merchants' images
    @Select("SELECT id,phone,password,address,rating,create_at,update_at FROM t_merchant WHERE (id IN (select " +
            "merchant_id from t_merchant_tag where tag_name = " +
            "#{tagName}))")
    List<Merchant> listByTagName(String tagName);
}
