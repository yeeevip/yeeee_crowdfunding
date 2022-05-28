package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class SysRoleMenu {
    /**
     * ID
     */
    private Long id;

    /**
     * 被授权对象ID
     */
    private Integer roleId;

    /**
     * 授权资源表ID
     */
    private Long menuId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;
}