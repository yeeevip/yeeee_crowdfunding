package com.yeeee.crowdfunding.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 系统调度日志
 * </p>
 *
 * @author yeeee
 * @since 2022-05-29
 */
@Data
@Accessors(chain = true)
@TableName("sys_qrtz_log")
public class SysQrtzLog {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * url
     */
    private String url;

    /**
     * 任务标识
     */
    private String jobKey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 运行时长
     */
    private Integer spendTime;

    /**
     * 下次执行时间
     */
    private Date nextFireTm;

    /**
     * 这次执行时间
     */
    private Date preFireTm;

    /**
     * 状态 (0：成功， 1：失败， 2: 异常)
     */
    private Integer status;

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

    /**
     * 逻辑删除
     */
    private Integer editFlag;


}
