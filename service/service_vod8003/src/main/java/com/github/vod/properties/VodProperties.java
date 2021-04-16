package com.github.vod.properties;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@Data
@TableName("three_party_info")
public class VodProperties {
    @TableField("id")
    private String keyId;

    @TableField("secret")
    private String keySecret;
}
