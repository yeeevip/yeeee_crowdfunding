package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yeeee.crowdfunding.model.entity.ProjectCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 21:42
 */
@Data
public class ProjectVO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("项目类型")
    private Integer projectType;

    @ApiModelProperty("标题")
    private String title;

    private Integer userId;

    @ApiModelProperty("简介")
    private String blurb;

    @ApiModelProperty("封面图片路径")
    private String coverPath;

    @ApiModelProperty("发起时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date launchDateRaising;

    @ApiModelProperty("目标集资")
    private Integer totalFundRaising;

    private Integer daysRaising;

    @ApiModelProperty("已经集资")
    private Integer hasFundRaising;

    @ApiModelProperty("-1:审核不通过 0:未审核 1:审核通过")
    private Integer hasAudits;

    @ApiModelProperty("项目状态 0：进行中  -1：集资失败 1：成功")
    private Integer hasFinish;

    @ApiModelProperty("搜索关键字")
    private String keyword;

    private UserVO seller;

    private ProjectCategoryVO categoryVO;

}
