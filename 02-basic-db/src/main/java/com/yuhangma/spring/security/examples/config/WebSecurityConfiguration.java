package com.yuhangma.spring.security.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Moore
 * @since 2019-07-31
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    /**
     * NOTE: 此项目和 01-basic 项目都没有前端页面，但是访问任何需要认证的页面，都会跳转到登录页面，这是因为在
     * {@link DefaultLoginPageGeneratingFilter#doFilter(ServletRequest, ServletResponse, FilterChain)} 方法中有一个判断，
     * 如果是登录的 url，则会生成默认的登录页面并且返回，而如果自定义了登录页面，则不会有这一段逻辑。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/index").permitAll()
                .and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义的 userDetailServiceImpl，需要实现 UserDetailsService 并覆盖 loadUserByUsername 方法
        auth.userDetailsService(userDetailsServiceImpl)
                // 密码不加密
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
