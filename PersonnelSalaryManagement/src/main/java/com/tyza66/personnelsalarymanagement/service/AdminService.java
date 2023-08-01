package com.tyza66.personnelsalarymanagement.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:04
 * Github: https://github.com/tyza66
 **/

public interface AdminService {

    //使用用户名和密码登录
    Boolean login(String username, String password);
}
