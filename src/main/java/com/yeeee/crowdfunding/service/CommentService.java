package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.CommentPageReqVO;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.PageVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 10:15
 */
public interface CommentService {

    Void frontAddComment(CommentVO commentVO);

    PageVO<CommentVO> frontCommentPageList(CommentPageReqVO pageReqVO);
}
