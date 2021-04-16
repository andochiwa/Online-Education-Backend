package com.github.center.thirdparty;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 04-16-22:00
 */
@Data
@TableName("three_party_info")
public class Github {

    private String id;

    private String secret;
}
