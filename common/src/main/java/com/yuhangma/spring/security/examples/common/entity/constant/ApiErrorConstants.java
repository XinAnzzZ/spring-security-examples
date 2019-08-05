package com.yuhangma.spring.security.examples.common.entity.constant;

import com.yuhangma.spring.security.examples.common.error.ApiError;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 全局常量类
 *
 * @author Moore
 * @since 2019-08-05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiErrorConstants {

    public static final ApiError USERNAME_HAS_BEEN_EXIST = ApiError.of(1000, "用户名已存在");
}
