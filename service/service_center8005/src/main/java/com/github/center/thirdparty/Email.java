package com.github.center.thirdparty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 04-21-15:59
 */
@Data
@TableName("three_party_info")
public class Email {

    @TableField("id")
    private String username;

    @TableField("secret")
    private String password;
}
