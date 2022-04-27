package com.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.app.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    @Delete("DELETE\n" +
            "FROM t_shopping_cart\n" +
            "WHERE t_shopping_cart.user_id = #{userId}\n" +
            "  AND t_shopping_cart.product_id IN\n" +
            "      (SELECT temp.product_id\n" +
            "       FROM (select t_shopping_cart.product_id\n" +
            "             FROM t_shopping_cart,\n" +
            "                  t_product\n" +
            "             WHERE t_shopping_cart.user_id = #{userId}\n" +
            "               AND t_shopping_cart.product_id = t_product.id\n" +
            "               AND t_product.merchant_id = #{merchantId}) temp)")
    int removeByMerchantId(Integer userId, Integer merchantId);


    @Select("SELECT t_product.id,\n" +
            "       t_product.merchant_id,\n" +
            "       t_product.name,\n" +
            "       t_product.price,\n" +
            "       t_product.percent_off,\n" +
            "       t_product.description,\n" +
            "       t_shopping_cart.quantity\n" +
            "FROM t_shopping_cart,\n" +
            "     t_product\n" +
            "WHERE t_shopping_cart.user_id = #{id}\n" +
            "  AND t_product.id = t_shopping_cart.product_id;")
    List<Map<String, Object>> listById(Integer id);

}
