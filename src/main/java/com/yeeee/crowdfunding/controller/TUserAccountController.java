package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.biz.TUserAccountBiz;
import com.yeeee.crowdfunding.model.request.TUserAccountFillRequest;
import com.yeeee.crowdfunding.model.vo.TUserAccountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 10:14
 */
@Api(tags = "用户钱包接口")
@RestController
public class TUserAccountController {

    @Resource
    private TUserAccountBiz tUserAccountBiz;

    @ApiOperation("我的钱包")
    @GetMapping("front/account/my")
    public CommonResult<TUserAccountVO> frontMyAccount() {
        return CommonResult.success(tUserAccountBiz.frontMyAccount());
    }

    @ApiOperation("钱包充值")
    @PostMapping("front/account/fill")
    public CommonResult<Void> frontFillAccount(@RequestBody TUserAccountFillRequest request) {
        return CommonResult.success(tUserAccountBiz.frontFillAccount(request));
    }

}
