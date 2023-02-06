package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/2/6 18:29
 */
@Data
public class TMsgListVO {

    private String id;

    private String content;

    private LocalDateTime createTime;

}
