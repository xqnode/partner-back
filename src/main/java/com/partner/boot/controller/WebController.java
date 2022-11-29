package com.partner.boot.controller;

import com.partner.boot.common.Result;
import com.partner.boot.entity.User;
import com.partner.boot.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "无权限接口列表")
@RestController
public class WebController {

    @Resource
    IUserService userService;

    @GetMapping(value = "/")
    @ApiOperation(value = "版本校验接口")
    public String version() {
        String ver = "partner-back-0.0.1-SNAPSHOT";  // 应用版本号
        Package aPackage = WebController.class.getPackage();
        String title = aPackage.getImplementationTitle();
        String version = aPackage.getImplementationVersion();
        if (title != null && version != null) {
            ver = String.join("-", title, version);
        }
        return ver;
    }

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User res = userService.login(user);
        return Result.success(res);
    }

    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User res = userService.register(user);
        return Result.success(res);
    }

}
