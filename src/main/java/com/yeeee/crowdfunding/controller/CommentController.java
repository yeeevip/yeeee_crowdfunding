package com.yeeee.crowdfunding.controller;

import com.yeeee.crowdfunding.api.CommonResult;
import com.yeeee.crowdfunding.biz.CommentBiz;
import com.yeeee.crowdfunding.model.vo.CommentPageReqVO;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.yeee.memo.integrate.base.websecurityoauth2.annotation.AnonymousAccess;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 10:14
 */
@RequiredArgsConstructor
@Api(tags = "评论接口")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentBiz commentBiz;

    @ApiOperation("新增评论")
    @PostMapping("front/comment/add")
    public CommonResult<Void> frontAddComment(@Validated @RequestBody CommentVO commentVO) {
        return CommonResult.success(commentService.frontAddComment(commentVO));
    }

    @ApiOperation("评论列表")
    @AnonymousAccess
    @PostMapping("front/comment/list")
    public CommonResult<PageVO<CommentVO>> frontCommentPageList(@Validated(CommentVO.PageListGroup.class) @RequestBody CommentPageReqVO pageReqVO) {
        return CommonResult.success(commentService.frontCommentPageList(pageReqVO));
    }

    @ApiOperation("评论列表")
    @PostMapping("front/comment/receive")
    public CommonResult<PageVO<CommentVO>> frontReceiveCommentPageList(@RequestBody CommentPageReqVO pageReqVO) {
        return CommonResult.success(commentBiz.frontReceiveCommentPageList(pageReqVO));
    }

    @ApiOperation("评论列表")
    @PostMapping("front/comment/send")
    public CommonResult<PageVO<CommentVO>> frontSendCommentPageList(@RequestBody CommentPageReqVO pageReqVO) {
        return CommonResult.success(commentBiz.frontSendCommentPageList(pageReqVO));
    }

}
