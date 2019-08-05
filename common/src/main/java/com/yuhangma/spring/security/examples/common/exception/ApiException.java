package com.yuhangma.spring.security.examples.common.exception;

import com.yuhangma.spring.security.examples.common.error.ApiError;
import lombok.Getter;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Getter
public class ApiException extends RuntimeException {

    private int code;

    public ApiException(ApiError error) {
        super(error.getMessage());

        this.code = error.getCode();
    }
}
