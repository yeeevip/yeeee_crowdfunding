package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 22:30
 */
@Data
public class CommentVO {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户")
    private String username;

    @ApiModelProperty("时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

}
