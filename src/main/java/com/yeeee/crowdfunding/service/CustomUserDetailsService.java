package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.dto.auth.Oauth2TokenDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 15:07
 */
public interface CustomUserDetailsService extends UserDetailsService{

    UserDetails loadUserByUsername(String username, String userType) throws UsernameNotFoundException;

    Oauth2TokenDTO oauthToken(String username, String password, String userType);

}
