package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Order;
import com.yeeee.crowdfunding.model.vo.BuyOrderVO;
import com.yeeee.crowdfunding.model.vo.SellerOrderVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class OrderConvert {

    public static BuyOrderVO order2VO(Order order) {
        if (order == null) {
            return null;
        }
        BuyOrderVO vo = new BuyOrderVO();
        vo.setId(order.getId());
        vo.setCode(order.getCode());
        vo.setCount(order.getCount());
        vo.setHasPay(order.getHasPay());
        vo.setHasSend(order.getHasSend());
        vo.setHasReceive(order.getHasReceive());
        vo.setOrderDate(order.getOrderDate());
        vo.setPayPrice(order.getPayPrice());
        return vo;
    }

    public static SellerOrderVO order2SellerVO(Order order) {
        if (order == null) {
            return null;
        }
        SellerOrderVO vo = new SellerOrderVO();
        vo.setId(order.getId());
        vo.setCode(order.getCode());
        vo.setUserId(order.getUserId());
        vo.setProjectId(order.getProjectId());
        vo.setReceiveInformation(order.getReceiveInformation());
        vo.setProjectRepayId(order.getProjectRepayId());
        vo.setCount(order.getCount());
        vo.setHasPay(order.getHasPay());
        vo.setHasSend(order.getHasSend());
        vo.setOrderDate(order.getOrderDate());
        vo.setPayPrice(order.getPayPrice());
        return vo;
    }

}
