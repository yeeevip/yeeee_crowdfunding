package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 17:20
 */
@Data
public class UserCheckVO {

    @NotBlank(message = "用户名不能空")
    private String username;
    @NotBlank(message = "密码不能空")
    private String password;
    private String code;
}
