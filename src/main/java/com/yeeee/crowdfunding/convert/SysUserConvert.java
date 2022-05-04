package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.dto.auth.SecurityUser;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.SysUserVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 20:18
 */
@Mapper(componentModel = "spring")
public interface SysUserConvert {

    @Mappings(@Mapping(source = "createTime", target = "dateOfRegistration"))
    UserVO sysUser2VO(SysUser sysUser);

    UserVO securityUser2VO(SecurityUser securityUser);

}
