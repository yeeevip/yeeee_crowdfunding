package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.CfUserInfoVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 20:18
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserVO user2VO(User user);

    CfUserInfoVO user2InfoVO(User user);

    User vo2Entity(UserVO userVO);

}
