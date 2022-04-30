package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Comment {
    /**
     * 评论id主键
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer project;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户id
     */
    private String username;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date time;
}