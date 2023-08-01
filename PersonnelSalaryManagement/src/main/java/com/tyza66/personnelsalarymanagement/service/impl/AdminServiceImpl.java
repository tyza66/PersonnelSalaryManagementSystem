package com.tyza66.personnelsalarymanagement.service.impl;

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
}
