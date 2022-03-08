package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_merchant_tag")
public class MerchantTag {
    private Integer merchantId;
    private String  tag;
}
