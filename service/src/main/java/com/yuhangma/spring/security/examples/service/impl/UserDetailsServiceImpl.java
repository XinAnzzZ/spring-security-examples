package com.yuhangma.spring.security.examples.service.impl;

import com.yuhangma.spring.security.examples.common.entity.model.User;
import com.yuhangma.spring.security.examples.common.repository.UserRepository;
import com.yuhangma.spring.security.examples.common.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository,
                                  UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        List<GrantedAuthority> userRoles = userRoleRepository.findAllByUserId(user.getId())
                .stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                .collect(toList());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(userRoles)
                .build();
    }
}
