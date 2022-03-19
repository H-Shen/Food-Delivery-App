package com.mealwrap.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_tag")
public class Tag implements Serializable {
    private static final long    serialVersionUID = 3567653491060394676L;
    @TableId(type = IdType.AUTO)
    private              Integer id;
    private              String  name;
    private              String  image;
}
