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
 * @since 2023-02-07
 */
@Data
@Accessors(chain = true)
@TableName("t_user_account")
public class TUserAccount {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 余额
     */
    private Long balance;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
