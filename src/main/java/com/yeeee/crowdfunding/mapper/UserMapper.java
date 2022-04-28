package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.dto.auth.SecurityUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/28 16:06
 */
@Mapper
public interface UserMapper {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "enabled", column = "state"),
            @Result(property = "authorities", column = "id", javaType = List.class,
                    many = @Many(select = "com.yeeee.crowdfunding.mapper.RoleMapper.findByUid"))
    })
    SecurityUser findByUsername(String username);

}
