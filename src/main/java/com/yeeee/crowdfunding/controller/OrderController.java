package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.BuyOrderPageReqVO;
import com.yeeee.crowdfunding.model.vo.BuyOrderVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:34
 */
@Api(tags = "订单中心")
@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("我下的订单")
    @PostMapping("front/buyer")
    public CommonResult<PageVO<BuyOrderVO>> getMyselfBuyOrderList(@RequestBody BuyOrderPageReqVO buyOrderPageReqVO) {
        return CommonResult.success(orderService.getMyselfBuyOrderList(buyOrderPageReqVO));
    }

    @ApiOperation("已卖出的订单")
    @PostMapping("front/seller")
    public CommonResult<Object> getMyselfOrderList() {
        return null;
    }

}
