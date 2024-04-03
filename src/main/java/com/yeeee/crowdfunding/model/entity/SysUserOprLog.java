package com.yeeee.crowdfunding.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2022-05-29
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_opr_log")
public class SysUserOprLog {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 路由地址
     */
    private String routePath;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * IP位置
     */
    private String ipLocation;

    /**
     *  请求方法
     */
    private String method;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求参数
     */
    private String accessParams;

    /**
     * 执行时间
     */
    private Long accessTime;

    /**
     * 返回数据
     */
    private String accessResult;

    /**
     * 异常信息
     */
    private String errorMessage;

    /**
     * 创建日期
     */
    private Date createTime;


}
