package com.yeeee.crowdfunding.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限标识
     */
    private String perm;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型 (0：菜单， 1：功能， 2：伪菜单)
     */
    private Integer type;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}