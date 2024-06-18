package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 20:17
 */
@Data
public class ProjectRepayVO {

    private Integer id;

    @ApiModelProperty("回报标题")
    private String payTitle;

    @ApiModelProperty("回报内容")
    private String payContent;

    @ApiModelProperty("回报类型")
    private String type;

    @ApiModelProperty("回报时间")
    private Integer time;

    @ApiModelProperty("回报金额")
    private Integer money;

}
