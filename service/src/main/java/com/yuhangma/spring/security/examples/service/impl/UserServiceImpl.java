package com.yuhangma.spring.security.examples.service.impl;

import com.yuhangma.spring.security.examples.common.entity.form.UserRegisterForm;
import com.yuhangma.spring.security.examples.common.entity.model.User;
import com.yuhangma.spring.security.examples.common.repository.UserRepository;
import com.yuhangma.spring.security.examples.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long registerUser(UserRegisterForm userRegisterForm) {
        if (userRepository.findByUsername(userRegisterForm.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        return userRepository.save(user).getId();
    }
}
