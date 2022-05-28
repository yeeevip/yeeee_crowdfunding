package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class SysUserInfoVO {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色ID")
    private Set<Integer> roleIds;

    @ApiModelProperty("组织ID")
    private Set<Integer> orgIds;

}
