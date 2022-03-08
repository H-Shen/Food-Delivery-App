package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_shopping_cart")
public class ShoppingCart {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
