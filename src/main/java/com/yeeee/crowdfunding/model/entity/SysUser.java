package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * description......
 * @author yeeee
 */
@Data
@Accessors(chain = true)
public class SysUser {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 花名
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门名称
     */
    private Integer deptId;

    /**
     * 岗位名称
     */
    private Integer jobId;

    /**
     * 状态：（0正常 1停用）
     */
    private Integer state;

    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 超级管理员
     */
    private Integer superAdmin;

}