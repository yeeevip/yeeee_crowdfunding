package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 22:28
 */
@Data
public class ProjectItemVO {

    @ApiModelProperty("标题")
    private String itemTitle;

    @ApiModelProperty("内容")
    private String itemContent;

}
