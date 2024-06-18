package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ProjectRepay {
    /**
     * 回报id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 回报标题
     */
    private String payTitle;

    /**
     * 回报内容
     */
    private String payContent;

    /**
     * 回报类型
     */
    private String type;

    /**
     * 回报时间
     */
    private Integer time;

    /**
     * 回报金额
     */
    private Integer money;
}