package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:42
 */
@Data
public class LunchProjectVO {

    @ApiModelProperty("项目类型")
    @NotNull(message = "项目类型不能为空")
    private Integer projectType;

    @ApiModelProperty("封面图片路径")
    @NotEmpty(message = "图片不能为空")
    private String coverPath;

    @ApiModelProperty("标题")
    @NotEmpty(message = "标题不能为空")
    private String title;

    @ApiModelProperty("简介")
    @NotEmpty(message = "简介不能为空")
    private String blurb;

    @ApiModelProperty("目标集资")
    @NotNull(message = "目标集资不能为空")
    @Min(value = 1, message = "目标集资金额不能为0")
    private Integer totalFundRaising;

    @ApiModelProperty("集资天数")
    @NotNull(message = "集资天数不能为空")
    @Min(value = 1, message = "集资天数不能为0")
    private Integer daysRaising;

    @ApiModelProperty("发起身份")
    @NotEmpty(message = "发起身份不能为空")
    private String shenfen;

    @ApiModelProperty("详情信息")
    private List<ProjectItemVO> itemVOList;

    @ApiModelProperty("回报信息")
    private List<ProjectRepayVO> repayVOList;

    private InitiatorPersonInfoVO initiatorPersonInfoVO;

    private InitiatorCompanyInfoVO initiatorCompanyInfoVO;

}
