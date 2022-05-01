package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class SysUserVO {

    @ApiModelProperty("用户名")
    private String username;

}
