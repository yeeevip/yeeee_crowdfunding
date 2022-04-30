package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:45
 */
@Data
public class BuyOrderVO {

    @ApiModelProperty("订单ID")
    private Integer id;

    @ApiModelProperty("订单编号")
    private String code;

    @ApiModelProperty("购买数量")
    private Integer count;

    @ApiModelProperty("是否支付")
    private Integer hasPay;

    @ApiModelProperty("是否发货")
    private Integer hasSend;

    @ApiModelProperty("下单时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @ApiModelProperty("支付金额")
    private Float payPrice;

    @ApiModelProperty("项目信息")
    private ProjectVO projectVO;

    @ApiModelProperty("回报信息")
    private ProjectRepayVO projectRepayVO;

    @ApiModelProperty("用户信息")
    private UserVO sellerVO;

}
