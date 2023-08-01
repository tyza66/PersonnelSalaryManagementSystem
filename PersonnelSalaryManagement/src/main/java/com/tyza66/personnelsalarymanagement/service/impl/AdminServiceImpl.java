package com.tyza66.personnelsalarymanagement.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.personnelsalarymanagement.mapper.AdminMapper;
import com.tyza66.personnelsalarymanagement.pojo.Admin;
import com.tyza66.personnelsalarymanagement.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:06
 * Github: https://github.com/tyza66
 **/

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public Boolean login(String username, String password) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username", username);
        adminQueryWrapper.eq("password", SecureUtil.sha1(password));
        Admin admin = getOne(adminQueryWrapper);
        return admin != null;
    }
}
