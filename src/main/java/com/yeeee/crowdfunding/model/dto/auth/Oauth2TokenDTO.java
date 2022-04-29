package com.yeeee.crowdfunding.model.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Oauth2获取Token返回信息封装
 */
@Data
public class Oauth2TokenDTO {

    @ApiModelProperty("访问令牌")
    private String token;

    @ApiModelProperty("刷令牌")
    private String refreshToken;

    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;

    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
