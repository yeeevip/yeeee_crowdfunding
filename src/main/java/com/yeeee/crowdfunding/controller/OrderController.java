package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.*;
import com.yeeee.crowdfunding.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("我下的订单")
    @PostMapping("front/order/buyer")
    public CommonResult<PageVO<BuyOrderVO>> getMyselfBuyOrderList(@RequestBody BuyOrderPageReqVO buyOrderPageReqVO) {
        return CommonResult.success(orderService.getMyselfBuyOrderList(buyOrderPageReqVO));
    }

    @ApiOperation("下单-支持项目")
    @PostMapping("front/order/create")
    public CommonResult<Void> frontCreateOrder(@Validated @RequestBody CreateOrderVO createOrderVO) {
        return CommonResult.success(orderService.frontCreateOrder(createOrderVO));
    }

    @ApiOperation("支付订单")
    @PostMapping("front/order/pay")
    public CommonResult<Void> frontPayOrder(@Validated @RequestBody PayVO payVO) {
        return CommonResult.success(orderService.frontPayOrder(payVO));
    }

    @ApiOperation("已卖出的订单")
    @PostMapping("front/order/seller")
    public CommonResult<PageVO<SellerOrderVO>> getSellerOrderList(@RequestBody BuyOrderPageReqVO buyOrderPageReqVO) {
        return CommonResult.success(orderService.getSellerOrderList(buyOrderPageReqVO));
    }

    @ApiOperation("管理员订单管理")
    @PostMapping("admin/order/page")
    public CommonResult<PageVO<SellerOrderVO>> getAdminOrderPageList(@RequestBody BuyOrderPageReqVO buyOrderPageReqVO) {
        return CommonResult.success(orderService.getAdminOrderPageList(buyOrderPageReqVO));
    }

}
