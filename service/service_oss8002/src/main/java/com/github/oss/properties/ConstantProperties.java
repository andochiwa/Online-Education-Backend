package com.github.oss.properties;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Data
@TableName("three_party_info")
public class ConstantProperties {

    @TableField("temp1")
    private String endpoint;

    @TableField("id")
    private String keyId;

    @TableField("secret")
    private String keySecret;

    @TableField("temp2")
    private String bucketName;
}
