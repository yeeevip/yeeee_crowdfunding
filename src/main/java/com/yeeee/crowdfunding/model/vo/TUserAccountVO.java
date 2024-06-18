package com.yeeee.crowdfunding.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/7 14:03
 */
@Data
public class TUserAccountVO {

    private String id;
    private Long balance;
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime updateTime;

}
