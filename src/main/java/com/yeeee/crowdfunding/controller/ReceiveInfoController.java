package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ReceiveInfoVO;
import com.yeeee.crowdfunding.model.vo.ReceivePageReqVO;
import com.yeeee.crowdfunding.service.ReceiveInfoService;
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
 * @since 2022/5/7 23:06
 */
@Api(tags = "收货地址")
@RequiredArgsConstructor
@RestController
@RequestMapping("receive")
public class ReceiveInfoController {

    private final ReceiveInfoService receiveInfoService;

    @ApiOperation("获取收货地址")
    @PostMapping("front/list")
    public CommonResult<PageVO<ReceiveInfoVO>> getReceivePageList(@RequestBody ReceivePageReqVO receivePageReqVO) {
        return CommonResult.success(receiveInfoService.getReceivePageList(receivePageReqVO));
    }

    @ApiOperation("修改收货地址")
    @PostMapping("front/update")
    public CommonResult<Void> updateReceiveInfo(@Validated @RequestBody ReceiveInfoVO receiveInfoVO) {
        return CommonResult.success(receiveInfoService.updateReceiveInfo(receiveInfoVO));
    }

}