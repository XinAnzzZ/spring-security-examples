package com.yuhangma.spring.security.examples.common.repository;

import com.yuhangma.spring.security.examples.common.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Moore
 * @since 2019-08-04
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find user by user's name
     *
     * @param username the user's name
     * @return the user
     */
    User findByUsername(String username);
}
