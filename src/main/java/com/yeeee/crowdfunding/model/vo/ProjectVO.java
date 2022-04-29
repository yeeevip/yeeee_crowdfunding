package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:42
 */
@Data
public class ProjectVO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("简介")
    private String blurb;

    @ApiModelProperty("封面图片路径")
    private String coverPath;

    @ApiModelProperty("目标集资")
    private Integer totalFundRaising;

    @ApiModelProperty("已经集资")
    private Integer hasFundRaising;

}
