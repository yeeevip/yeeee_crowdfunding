package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 22:30
 */
@Data
public class CommentVO {

    private Integer id;

    @NotNull(message = "projectId不能空", groups = {SaveGroup.class, PageListGroup.class})
    @ApiModelProperty("项目ID")
    private Integer projectId;

    private String projectTitle;

    @NotBlank(message = "内容不能空", groups = SaveGroup.class)
    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户")
    private String username;

    @ApiModelProperty("时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    public interface SaveGroup {}

    public interface PageListGroup {}

}
