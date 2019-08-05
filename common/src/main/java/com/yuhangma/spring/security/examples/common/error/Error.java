package com.yuhangma.spring.security.examples.common.error;

/**
 * @author Moore
 * @since 2019-08-05
 */
public interface Error {

    /**
     * get err code
     *
     * @return the err code
     */
    int getCode();

    /**
     * get err msg
     *
     * @return the err msg
     */
    String getMessage();
}
