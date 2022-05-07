package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReceiveInfoVO {
    /**
     * 收货地址id主键
     */
    @NotNull(message = "ID不能为空")
    private Integer id;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    private Integer setDefault;

}