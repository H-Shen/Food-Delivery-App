package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_shopping_cart")
public class ShoppingCart implements Serializable {
    private static final long    serialVersionUID = 3567653491060394675L;
    private              Integer userId;
    private              Integer productId;
    private              Integer quantity;
}
