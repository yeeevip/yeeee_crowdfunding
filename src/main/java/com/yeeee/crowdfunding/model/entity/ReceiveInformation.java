package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReceiveInformation {
    /**
     * 收货地址id主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

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

    /**
     * 默认
     */
    private Integer setDefault;
}