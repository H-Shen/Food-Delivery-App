package com.mealwrap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mealwrap.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    @Delete("delete\n" +
            "from t_shopping_cart\n" +
            "where t_shopping_cart.user_id = #{userId}\n" +
            "  and t_shopping_cart.product_id in\n" +
            "      (select temp.product_id\n" +
            "       from (select t_shopping_cart.product_id\n" +
            "             from t_shopping_cart,\n" +
            "                  t_product\n" +
            "             where t_shopping_cart.user_id = #{userId}\n" +
            "               and t_shopping_cart.product_id = t_product.id\n" +
            "               and t_product.merchant_id = #{merchantId}) temp)")
    int removeByMerchantId(Integer userId, Integer merchantId);

}
