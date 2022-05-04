package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuVO {
    /**
     * ID
     */
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

    List<SysMenuVO> children;

}