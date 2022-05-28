package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class SysUserUpdPwdVO {

    @ApiModelProperty("旧密码")
    @NotEmpty(message = "旧密码不能空")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotEmpty(message = "新密码1不能空")
    private String newPassword;

}
