package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class CommentConvert {

    public static CommentVO comment2VO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentVO vo = new CommentVO();
        vo.setId(comment.getId());
        vo.setProjectId(comment.getProject());
        vo.setContent(comment.getContent());
        vo.setUsername(comment.getUsername());
        vo.setTime(comment.getTime());
        return vo;
    }

    public static Comment vo2Comment(CommentVO commentVO) {
        if (commentVO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setProject(commentVO.getProjectId());
        comment.setContent(commentVO.getContent());
        comment.setUsername(commentVO.getUsername());
        comment.setTime(commentVO.getTime());
        return comment;
    }

}
