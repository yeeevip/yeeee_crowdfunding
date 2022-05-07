package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 17:20
 */
@Data
public class UpdatePasswordVO {

    @NotBlank(message = "旧密码不能空")
    private String oldPassword;
    @NotBlank(message = "新密码不能空")
    private String newPassword;
}
