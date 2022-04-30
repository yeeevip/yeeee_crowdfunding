package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Project {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String blurb;

    /**
     * 封面图片路径
     */
    private String coverPath;

    /**
     * 目标集资
     */
    private Integer totalFundRaising;

    /**
     * 已经集资
     */
    private Integer hasFundRaising;

    /**
     * 发起人ID
     */
    private Integer userId;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 发起时间
     */
    private Date launchDateRaising;

    /**
     * 集资天数
     */
    private Integer daysRaising;

    /**
     * 项目状态 0：进行中  -1：集资失败 1：成功
     */
    private Integer hasFinish;

    /**
     * -1:审核不通过 0:未审核 1:审核通过
     */
    private Integer hasAudits;

    /**
     * 是否首页展示
     */
    private Integer hasIndex;

    /**
     * 发起身份
     */
    private String shenfen;

    /**
     * 上线时间，审核通过后即上线
     */
    private Date onlineTime;

    /**
     * 结算状态    -1：不可结算 0：未结算  1：已结算
     */
    private Integer isSettlement;

    private String keyword;

}