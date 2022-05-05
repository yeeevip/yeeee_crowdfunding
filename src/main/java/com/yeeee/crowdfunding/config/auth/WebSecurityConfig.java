package com.yeeee.crowdfunding.config.auth;

import com.yeeee.crowdfunding.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity配置类
 *
 * @author yeeee
 * @since 2022/4/28 16:14
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(customAuthenticationProvider())
                .userDetailsService(userDetailsService)
                // 加密策略
                .passwordEncoder(passwordEncoder);
    }

    // AuthenticationManager对象在OAuth2认证服务中要使用，提取放入IOC容器中
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置自定义的CustomAuthenticationProvider
     */
    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        CustomAuthenticationProvider customAuthenticationProvider= new CustomAuthenticationProvider();
        customAuthenticationProvider.setUserDetailsService(userDetailsService);
        customAuthenticationProvider.setHideUserNotFoundExceptions(false);
        customAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return customAuthenticationProvider;
    }

}
