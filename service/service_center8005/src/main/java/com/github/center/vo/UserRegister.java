package com.github.center.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
@Data
public class UserRegister {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;
}
