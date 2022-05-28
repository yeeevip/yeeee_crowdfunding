package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.dto.auth.SecurityUser;
import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.vo.SysUserEditVO;
import com.yeeee.crowdfunding.model.vo.SysUserInfoVO;
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

    UserVO sysUser2VO(SysUser sysUser);

    UserVO securityUser2VO(SecurityUser securityUser);

    SysUser editVO2Entity(SysUserEditVO editVO);

    SysUserInfoVO entity2InfoVO(SysUser sysUser);

}
