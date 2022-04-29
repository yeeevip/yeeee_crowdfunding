package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SysRole {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态 （0正常 1停用）
     */
    private Integer status;

    /**
     * 简介
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}