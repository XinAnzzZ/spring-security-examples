package com.yuhangma.spring.security.examples.controller;

import com.yuhangma.spring.security.examples.common.entity.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Moore
 * @since 2019-08-02
 */
@RestController
public class IndexController {

    @GetMapping(value = {"/", "index"})
    public ResultJson index() {
        return ResultJson.ok();
    }

    @GetMapping("user/index")
    public ResultJson userIndex() {
        return ResultJson.msg("This is user interface, only have a USER role or ADMIN role to access! ");
    }

    @GetMapping("admin/index")
    public ResultJson adminIndex() {
        return ResultJson.msg("This is admin interface, only have a ADMIN role to access! ");
    }
}
