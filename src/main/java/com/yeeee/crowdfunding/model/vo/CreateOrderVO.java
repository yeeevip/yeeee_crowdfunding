package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 23:35
 */
@Data
public class CreateOrderVO {

    @NotNull
    private Integer repayId;

    @NotNull
    @Min(value = 1, message = "购买份数应该大于0")
    private Integer payCount;

    @NotNull
    private ReceiveInfoVO receiveInfoVO;

}
