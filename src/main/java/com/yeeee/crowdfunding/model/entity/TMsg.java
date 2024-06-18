package com.yeeee.crowdfunding.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2023-02-06
 */
@Data
@Accessors(chain = true)
@TableName("t_msg")
public class TMsg {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1：项目
     */
    private Integer subjectType;

    private String subjectId;

    private String content;

    private String target;

    private Integer hasRead;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;


}
