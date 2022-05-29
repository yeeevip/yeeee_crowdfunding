package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SysMenuVO {
    /**
     * ID
     */
    @NotNull(message = "ID不能空", groups = {Info.class, Edit.class})
    private Long id;

    @NotNull(message = "IDS不能空", groups = {Del.class})
    @Size(min = 1, message = "IDS不能空", groups = {Del.class})
    private List<Long> ids;

    /**
     * 父ID
     */
    private Long pid;

    private String pnm;

    /**
     * 名称
     */
    @NotBlank(message = "name不能空", groups = {Base.class})
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
    @NotNull(message = "type不能空", groups = {Base.class})
    private Integer type;

    List<SysMenuVO> children;

    public interface Base {}
    public interface Add extends SysMenuVO.Base {}
    public interface Edit extends SysMenuVO.Base {}
    public interface Info {}
    public interface Del {}

}