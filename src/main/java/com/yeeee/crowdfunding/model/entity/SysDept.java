package com.yeeee.crowdfunding.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yeeee
 * @since 2022-05-29
 */
@Data
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDept {

    /**
     * 部门id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门代码
     */
    private String code;

    /**
     * 父部门id
     */
    private Integer pid;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


}
