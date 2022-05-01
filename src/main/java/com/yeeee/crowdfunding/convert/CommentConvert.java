package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Comment;
import com.yeeee.crowdfunding.model.vo.CommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface CommentConvert {

    CommentVO comment2VO(Comment comment);

    @Mappings(@Mapping(source = "projectId", target = "project"))
    Comment vo2Comment(CommentVO commentVO);

}
