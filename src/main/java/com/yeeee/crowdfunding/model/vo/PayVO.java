package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/1 23:35
 */
@Data
public class PayVO {

    @NotNull
    private Integer subjectId;

    private Integer payCount;

}
