package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 22:46
 */
@Data
public class ProjectProgressVO {

    @ApiModelProperty("最新进展内容")
    private String content;

    @ApiModelProperty("发布时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    private String publishDateStr;

    @ApiModelProperty("发布人")
    private String pubUser;

    @NotNull
    private Integer projectId;

}
