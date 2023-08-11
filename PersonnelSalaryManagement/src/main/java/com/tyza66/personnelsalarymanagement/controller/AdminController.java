package com.tyza66.personnelsalarymanagement.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.personnelsalarymanagement.pojo.Admin;
import com.tyza66.personnelsalarymanagement.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:10
 * Github: https://github.com/tyza66
 **/

@Api(tags = "管理员登录模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public JSON login(@RequestBody Admin admin) {
        JSONObject obj = JSONUtil.createObj();
        Boolean login = adminService.login(admin.getUsername(), admin.getPassword());
        if (login) {
            obj.put("code", 200);
            obj.put("msg", "登录成功");
            StpUtil.login(0);
            StpUtil.getSession().set("username", admin.getUsername());
            obj.put("token", StpUtil.getTokenValue());
        } else {
            obj.put("code", 400);
            obj.put("msg", "登录失败");
        }
        return obj;
    }

    @ApiOperation("管理员退出登录")
    @GetMapping("/logout")
    public JSON logout() {
        JSONObject obj = JSONUtil.createObj();
        StpUtil.logout();
        obj.put("code", 200);
        obj.put("msg", "退出成功");
        return obj;
    }

    @ApiOperation("管理员注册")
    @PostMapping("/register")
    public JSON register(@RequestBody Admin admin) {
        JSONObject obj = JSONUtil.createObj();
        Boolean register = adminService.register(admin.getUsername(), admin.getPassword());
        if (register) {
            obj.put("code", 200);
            obj.put("msg", "注册成功");
        } else {
            obj.put("code", 400);
            obj.put("msg", "注册失败,可能用户名已存在");
        }
        return obj;
    }

    @ApiOperation("检查当前登录状态")
    @GetMapping("/check")
    public JSON check() {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            obj.put("code", 200);
            obj.put("msg", "已登录");
            obj.put("username", StpUtil.getSession().get("username"));
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }
}
