package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 21:42
 */
@Data
public class AuditProjectVO {

    @NotNull
    private Integer projectId;

    @NotNull
    private Integer hasAudits;

}
