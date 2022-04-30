package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.BuyOrderPageReqVO;
import com.yeeee.crowdfunding.model.vo.BuyOrderVO;
import com.yeeee.crowdfunding.model.vo.PageVO;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:34
 */
public interface OrderService {

    PageVO<BuyOrderVO> getMyselfBuyOrderList(BuyOrderPageReqVO buyOrderPageReqVO);

}
