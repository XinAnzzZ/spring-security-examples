package com.yuhangma.spring.security.examples.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Getter
@AllArgsConstructor
public class ApiError implements Error {

    private int code;

    private String message;

    public static ApiError of(int code, String message) {
        return new ApiError(code, message);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
