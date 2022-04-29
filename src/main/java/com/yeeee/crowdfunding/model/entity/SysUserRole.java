package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SysUserRole {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

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