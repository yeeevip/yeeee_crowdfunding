package com.yeeee.crowdfunding.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/6 17:31
 */
@Data
public class TUserAccountFillRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long balance;

}
