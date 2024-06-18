package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProjectDetail {

    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 标题
     */
    private String itemTitle;

    /**
     * 内容
     */
    private String itemContent;
}