package com.yuhangma.spring.security.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置器
 * {@link EnableWebSecurity} 表示将这个类作为配置类
 *
 * @author Moore
 * @since 2019-07-31
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * NOTE: 此方法仅作为配置的示例，不会对此项目产生任何作用。
     * <p>
     * 鉴权配置示例，所有的配置都在 {@link AbstractAuthenticationFilterConfigurer} 中找到，
     * 通过这个类的名字可以知道，这个是抽象的认证过滤器配置器，主要是用来配置认证与鉴权相关的内容。
     */
    @SuppressWarnings("unused")
    protected void configureExample(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 任何 url 匹配以下 pattern 的都是允许直接访问的
                .antMatchers("/", "/index").permitAll()
                // 任何请求都需要进行认证
                .anyRequest().authenticated()
                // user 开头的 url 需要 USER 角色，admin 开头的需要 ADMIN 角色
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                // common 开头的 url 任意拥有 USER、ADMIN 角色其中的一个即可
                .antMatchers("/common/**").hasAnyRole("USER", "ADMIN")
                // /user/detail 开头的 url 需要 user:detail 权限或者 admin:auth 权限
                .antMatchers("/user/detail/**").hasAnyAuthority("user:detail", "admin:auth")
                // /admin/auth 开头的 url 必须要有 admin:auth 权限
                .antMatchers("/admin/auth").hasAuthority("admin:auth")
                // 配置登录配置器，默认会增加一个 FormLoginConfigurer，它默认的登录过滤器是 UsernamePasswordAuthenticationFilter
                .and().formLogin()
                // 配置登录页地址，如果不配置，默认是“login”，并且会返回一个默认的登录页面
                .loginPage("/user/login")
                // 登录请求携带的用户名参数，默认 username
                .usernameParameter("username")
                // 密码，默认 password
                .passwordParameter("password")
                // 配置的登录请求的地址，和上面不同的是，上面的是登录页面，而这个配置的是登录请求
                .loginProcessingUrl("/ajax/login")
                // 登录成功默认跳转的页面
                .defaultSuccessUrl("/user/index")
                // 注销登录，允许任何人访问
                .and().logout()
                // 关闭 csrf，默认开启。关于 csrf，详见 <a href="https://zhuanlan.zhihu.com/p/22521378">CSRF</a>
                .and().csrf().disable();
    }

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

    /**
     * 在内存中创建两个用户。参见父类方法的注释内容。
     *
     * @see WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("the_username").password("the_password").roles("USER")
                .and().withUser("admin").password("admin").roles("ADMIN");
    }

    /**
     * 密码加密器。
     * <p>
     * {@link NoOpPasswordEncoder} 是对密码进行明文处理，在 Spring Security 5.x 之后，强制要求密码要进行加密处理，
     * 所以这个类已经被标识为过期，本项目主要为了演示认证流程，故密码使用明文处理。之后的项目全部使用密文。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
