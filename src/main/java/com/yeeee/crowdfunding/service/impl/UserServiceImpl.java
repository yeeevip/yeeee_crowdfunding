package com.yeeee.crowdfunding.service.impl;

import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.UserMapper;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 16:19
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Void register(String username, String password, String code) {
        User user = userMapper.getOne(new User().setUsername(username));
        if (user != null) {
            throw new BizException("用户名已经存在！！！");
        }
        User saveUser = new User();
        saveUser.setUsername(username);
        saveUser.setPassword(passwordEncoder.encode(password));
        userMapper.insert(saveUser);
        return null;
    }

}
