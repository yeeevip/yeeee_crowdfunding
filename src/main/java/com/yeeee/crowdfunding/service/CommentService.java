package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.dto.CommentDto;
import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentPageReqVO;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.PageVO;

import java.util.List;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 10:15
 */
public interface CommentService extends IService<Comment> {

    Void frontAddComment(CommentVO commentVO);

    PageVO<CommentVO> frontCommentPageList(CommentPageReqVO pageReqVO);

    List<CommentVO> getProjectCommentList(CommentDto query);
}
