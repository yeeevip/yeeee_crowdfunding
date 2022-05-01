package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.*;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:34
 */
public interface OrderService {

    PageVO<BuyOrderVO> getMyselfBuyOrderList(BuyOrderPageReqVO buyOrderPageReqVO);

    Void frontCreateOrder(CreateOrderVO createOrderVO);

    Void frontPayOrder(PayVO payVO);

    PageVO<SellerOrderVO> getSellerOrderList(BuyOrderPageReqVO buyOrderPageReqVO);
}
