package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.Order;
import com.yeeee.crowdfunding.model.vo.*;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 19:34
 */
public interface OrderService extends IService<Order> {

    PageVO<BuyOrderVO> getMyselfBuyOrderList(BuyOrderPageReqVO buyOrderPageReqVO);

    Void frontCreateOrder(CreateOrderVO createOrderVO);

    Void frontPayOrder(PayVO payVO);

    PageVO<SellerOrderVO> getSellerOrderList(BuyOrderPageReqVO buyOrderPageReqVO);

    PageVO<SellerOrderVO> getAdminOrderPageList(BuyOrderPageReqVO buyOrderPageReqVO);
}
