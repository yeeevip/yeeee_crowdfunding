package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

@Data
public class ReceiveInfoVO {
    /**
     * 收货地址id主键
     */
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
}