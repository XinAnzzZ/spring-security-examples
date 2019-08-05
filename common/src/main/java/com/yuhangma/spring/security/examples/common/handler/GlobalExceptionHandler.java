package com.yuhangma.spring.security.examples.common.handler;

import com.yuhangma.spring.security.examples.common.entity.ResultJson;
import com.yuhangma.spring.security.examples.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultJson handleAllException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         RuntimeException exception) {
        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            log.error(String.format(""));
            return ResultJson.fail(apiException.getCode(), apiException.getMessage());
        }
        return ResultJson.fail(500, "Sorry, I don't what happened, please try again later!");
    }
}
