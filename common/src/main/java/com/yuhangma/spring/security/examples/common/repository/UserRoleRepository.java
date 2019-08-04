package com.yuhangma.spring.security.examples.common.repository;

import com.yuhangma.spring.security.examples.common.entity.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Moore
 * @since 2019-08-04
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
