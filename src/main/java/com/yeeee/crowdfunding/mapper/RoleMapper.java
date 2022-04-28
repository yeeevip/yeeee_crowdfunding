package com.yeeee.crowdfunding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 16:03
 */
@Mapper
public interface RoleMapper {

    @Select("select r.code " +
            "FROM sys_role r,sys_user_role ur " +
            "WHERE r.id=ur.role_id AND ur.user_id=#{uid}")
    List<SimpleGrantedAuthority> findByUid(Integer uid);

}
