package com.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.app.entity.MerchantTag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MerchantTagMapper extends BaseMapper<MerchantTag> {

    @Select("SELECT id,phone,password,name,description,address,rating,create_at,update_at FROM t_merchant WHERE id" +
            " IN (select merchant_id from t_merchant_tag where tag_name = #{tagName})")
    List<Map<String, Object>> listByTagName(String tagName);
}
