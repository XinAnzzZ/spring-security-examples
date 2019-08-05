package com.yuhangma.spring.security.examples.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Moore
 * @since 2019-08-01
 */
@Data
public class ResultJson<T> {

    @NonNull
    private Integer code;

    @NonNull
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static final String SUCCESS = "success";

    public static final int OK = 200;

    private ResultJson(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultJson ok() {
        return new ResultJson(OK, SUCCESS);
    }

    public static <R> ResultJson<R> ok(R data) {
        ResultJson<R> resultJson = new ResultJson<>(OK, SUCCESS);
        resultJson.setData(data);
        return resultJson;
    }

    public static ResultJson msg(String msg) {
        return new ResultJson(OK, msg);
    }

    public static ResultJson fail(int code, String message) {
        return new ResultJson(code, message);
    }
}
