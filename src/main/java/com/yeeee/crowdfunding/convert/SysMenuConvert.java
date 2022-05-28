package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.SysMenu;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 20:18
 */
@Mapper(componentModel = "spring")
public interface SysMenuConvert {

    SysMenuVO entity2VO(SysMenu sysMenu);

    SysMenu vo2Entity(SysMenuVO sysMenuVO);

}
