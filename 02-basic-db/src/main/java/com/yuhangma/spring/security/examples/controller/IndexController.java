package com.yuhangma.spring.security.examples.controller;

import com.yuhangma.spring.security.examples.common.entity.ResultJson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * 获取已授权的信息
     */
    @GetMapping("/auth/info")
    public ResultJson<Authentication> getAuthenticationInfo() {
        return ResultJson.ok(getAuthentication());
    }

    @GetMapping("user/index")
    public ResultJson userIndex() {
        return ResultJson.msg("This is user interface, only have a USER role or ADMIN role to access! ");
    }

    @GetMapping("admin/index")
    public ResultJson adminIndex() {
        return ResultJson.msg("This is admin interface, only have a ADMIN role to access! ");
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
