package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.biz.TMsgServiceBiz;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.request.PageRequest;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.TMsgListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.yeee.memo.integrate.base.websecurityoauth2.annotation.AnonymousAccess;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 10:14
 */
@RequiredArgsConstructor
@Api(tags = "消息接口")
@RestController
public class TMsgController {

    private final TMsgServiceBiz tMsgServiceBiz;

    @ApiOperation("消息列表")
    @AnonymousAccess
    @PostMapping("front/msg/list")
    public CommonResult<PageVO<TMsgListVO>> frontMsgPageList(@RequestBody PageRequest request) {
        return CommonResult.success(tMsgServiceBiz.frontMsgPageList(request));
    }

    @ApiOperation("读取消息")
    @AnonymousAccess
    @PostMapping("front/msg/read")
    public CommonResult<Void> frontReadMsgList(@RequestBody IdsRequest request) {
        return CommonResult.success(tMsgServiceBiz.frontReadMsgList(request));
    }

}
