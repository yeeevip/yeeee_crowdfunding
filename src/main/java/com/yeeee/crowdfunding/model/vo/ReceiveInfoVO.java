package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReceiveInfoVO {
    /**
     * 收货地址id主键
     */
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 收货人
     */
    @NotEmpty(message = "收货人不能为空", groups = {Add.class})
    private String receiver;

    /**
     * 电话
     */
    @NotEmpty(message = "电话不能为空", groups = {Add.class})
    private String phone;

    /**
     * 地址
     */
    @NotEmpty(message = "收货地址不能为空", groups = {Add.class})
    private String address;

    private Integer setDefault;

    public interface Add{}
    public interface Update{}

}