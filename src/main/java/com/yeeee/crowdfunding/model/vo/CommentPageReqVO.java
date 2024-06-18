package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 12:30
 */
@Data
public class CommentPageReqVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private CommentVO commentVO;

}
