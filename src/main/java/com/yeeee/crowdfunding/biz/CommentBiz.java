package com.yeeee.crowdfunding.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.CommentConvert;
import com.yeeee.crowdfunding.model.dto.CommentDto;
import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentPageReqVO;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.service.CommentService;
import org.springframework.stereotype.Component;
import vip.yeee.memo.integrate.base.websecurityoauth2.context.SecurityContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/7 16:57
 */
@Component
public class CommentBiz {

    @Resource
    private CommentService commentService;
    @Resource
    private CommentConvert commentConvert;

    public PageVO<CommentVO> frontReceiveCommentPageList(CommentPageReqVO pageReqVO) {
        Page<Comment> page = PageHelper.startPage(pageReqVO.getPageNum(), pageReqVO.getPageSize());
        CommentDto query = new CommentDto();
        query.setProjectUserId(Integer.valueOf(SecurityContext.getCurUserId()));
        List<CommentVO> commentVOList = commentService.getProjectCommentList(query);
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), commentVOList);
    }

    public PageVO<CommentVO> frontSendCommentPageList(CommentPageReqVO pageReqVO) {
        Page<Comment> page = PageHelper.startPage(pageReqVO.getPageNum(), pageReqVO.getPageSize());
        CommentDto query = new CommentDto();
        query.setUserId(Integer.valueOf(SecurityContext.getCurUserId()));
        List<CommentVO> commentVOList = commentService.getProjectCommentList(query);
        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), commentVOList);
    }
}
