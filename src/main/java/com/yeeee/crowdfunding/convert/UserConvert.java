package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectRepay;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.ProjectRepayVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 20:18
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserVO user2VO(User user);

}
