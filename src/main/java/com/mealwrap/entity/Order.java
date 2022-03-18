package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer       id;
    private Integer       userId;
    private Integer       merchantId;
    private String        address;
    private String        phone;
    private Integer       paymentMethod;
    @ApiModelProperty(dataType = "String", required = true, example = "2020-01-01 13:30:31")
    private LocalDateTime deliveryTime;
    private Integer       deliveryMethod;
    private Double        totalPrice;
    private Double        deliveryFee;
    private Double        tax;
    private Double        tip;
    private String        comment;
    @ApiModelProperty(dataType = "String", required = true, example = "2020-01-01 13:30:31")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
    @ApiModelProperty(dataType = "String", required = true, example = "2020-01-01 13:30:31")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;
}
