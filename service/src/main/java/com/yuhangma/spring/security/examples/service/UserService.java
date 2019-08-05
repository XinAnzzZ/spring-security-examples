package com.yuhangma.spring.security.examples.service;

import com.yuhangma.spring.security.examples.common.entity.form.UserRegisterForm;

/**
 * @author Moore
 * @since 2019-08-05
 */
public interface UserService {

    /**
     * user register
     *
     * @param userRegisterForm the user register's form data
     * @return the user's id
     */
    Long registerUser(UserRegisterForm userRegisterForm);
}
