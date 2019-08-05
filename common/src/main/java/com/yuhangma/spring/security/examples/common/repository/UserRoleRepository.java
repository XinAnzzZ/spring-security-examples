package com.yuhangma.spring.security.examples.common.repository;

import com.yuhangma.spring.security.examples.common.entity.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Moore
 * @since 2019-08-04
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * find by user id
     *
     * @param id the user id
     * @return the user's role list
     */
    List<UserRole> findAllByUserId(Long id);
}
