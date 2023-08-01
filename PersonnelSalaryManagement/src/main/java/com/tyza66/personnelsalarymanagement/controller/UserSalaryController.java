package com.tyza66.personnelsalarymanagement.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.service.UserSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:13
 * Github: https://github.com/tyza66
 **/


@Api(tags = "员工薪资管理模块")
@RestController
@RequestMapping("/salary")
public class UserSalaryController {

    @Autowired
    private UserSalaryService userSalaryService;

    @ApiOperation("分页查询员工工资信息")
    @GetMapping("/list")
    public JSON list(@ApiParam("页码") @RequestParam Integer page,
                     @ApiParam("每页显示条数") @RequestParam Integer limit) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            obj.put("code", 200);
            obj.put("msg", "查询成功");
            obj.put("data", userSalaryService.getUserSalary(page, limit));
            obj.put("count", userSalaryService.getPages(page, limit));
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

    @ApiOperation("添加员工薪资信息")
    @PostMapping("/add")
    public JSON add(@RequestBody UserSalary userSalary) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            Boolean aBoolean = userSalaryService.addUserSalary(userSalary);
            if (aBoolean) {
                obj.put("code", 200);
                obj.put("msg", "添加成功");
            } else {
                obj.put("code", 201);
                obj.put("msg", "添加失败");
            }
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

    @ApiOperation("删除员工薪资信息")
    @PostMapping("/delete")
    public JSON delete(@ApiParam("员工薪资信息id") @RequestBody UserSalary userSalary) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            Boolean aBoolean = userSalaryService.deleteUserSalary(userSalary.getId());
            if (aBoolean) {
                obj.put("code", 200);
                obj.put("msg", "删除成功");
            } else {
                obj.put("code", 201);
                obj.put("msg", "删除失败");
            }
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

    @ApiOperation("修改员工薪资信息")
    @PostMapping("/update")
    public JSON update(@RequestBody UserSalary userSalary) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            Boolean aBoolean = userSalaryService.updateUserSalary(userSalary);
            if (aBoolean) {
                obj.put("code", 200);
                obj.put("msg", "修改成功");
            } else {
                obj.put("code", 201);
                obj.put("msg", "修改失败");
            }
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

    @ApiOperation("通过id查询员工薪资信息")
    @GetMapping("/getById")
    public JSON getById(@RequestParam Integer id) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            obj.put("code", 200);
            obj.put("msg", "查询成功");
            obj.put("data", userSalaryService.getUserSalaryById(id));
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

    @ApiOperation("通过用户名查询员工薪资信息")
    @GetMapping("/getByUsername")
    public JSON getByUsername(@RequestParam String username) {
        JSONObject obj = JSONUtil.createObj();
        if (StpUtil.isLogin()) {
            obj.put("code", 200);
            obj.put("msg", "查询成功");
            obj.put("data", userSalaryService.getUserSalaryByUsername(username));
        } else {
            obj.put("code", 201);
            obj.put("msg", "未登录");
        }
        return obj;
    }

}
