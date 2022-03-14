package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer       orderId;
    private Integer       buyerId;
    private Integer       merchantId;
    private String        address;
    private String        phone;
    private Integer       paymentMethod;
    private LocalDateTime deliveryTime;
    private Integer       deliveryMethod;
    private BigDecimal    totalPrice;
    private BigDecimal    deliveryFee;
    private BigDecimal    tax;
    private BigDecimal    tip;
    private String        comment;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
}
