package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer merchantId;
    private String  name;
    private Double  price;
    private Integer percentOff;
    private byte[]  image;
}
