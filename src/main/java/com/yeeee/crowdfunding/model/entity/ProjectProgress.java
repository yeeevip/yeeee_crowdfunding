package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ProjectProgress {

    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 最新进展内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date publishDate;

    private String pubUser;
}