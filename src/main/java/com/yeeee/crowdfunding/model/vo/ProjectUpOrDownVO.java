package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/4/29 15:29
 */
@Data
public class ProjectUpOrDownVO {

    @NotNull
    private Integer id;
    @NotNull
    private Integer hasDown;

}
