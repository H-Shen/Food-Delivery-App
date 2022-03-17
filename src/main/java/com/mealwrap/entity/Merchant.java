package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_merchant")
public class Merchant {
    @TableId(type = IdType.AUTO)
    private Integer       id;
    private String        phone;
    private String        password;
    private String        address;
    private Integer       rating;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;
    private byte[]        image;
}
