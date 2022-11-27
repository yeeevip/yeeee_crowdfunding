package com.yeeee.crowdfunding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.CommentConvert;
import com.yeeee.crowdfunding.mapper.CommentMapper;
import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentPageReqVO;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.service.CommentService;
import com.yeeee.crowdfunding.utils.BusinessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.yeee.memo.integrate.common.websecurity.context.SecurityContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/1 10:15
 */
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentConvert commentConvert;

    @Override
    public Void frontAddComment(CommentVO commentVO) {

        Comment comment = commentConvert.vo2Comment(commentVO);
        comment.setUserId(BusinessUtils.getCurUserId());
        comment.setUsername(SecurityContext.getCurUser().getUsername());
        comment.setTime(new Date());

        commentMapper.insert(comment);

        return null;
    }

    @Override
    public PageVO<CommentVO> frontCommentPageList(CommentPageReqVO pageReqVO) {
        Page<Comment> page = PageHelper.startPage(pageReqVO.getPageNum(), pageReqVO.getPageSize());
        List<CommentVO> commentVOList = Optional.ofNullable(commentMapper.getList(new Comment().setProject(pageReqVO.getCommentVO().getProjectId()))).orElseGet(Lists::newArrayList)
                .stream()
                .map(commentConvert::comment2VO)
                .collect(Collectors.toList());
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), commentVOList);
    }
}
