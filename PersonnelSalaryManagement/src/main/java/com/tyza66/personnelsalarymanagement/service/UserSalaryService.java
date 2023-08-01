package com.tyza66.personnelsalarymanagement.service;

import com.tyza66.personnelsalarymanagement.pojo.UserSalary;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:11
 * Github: https://github.com/tyza66
 **/

public interface UserSalaryService {

    //分页查询所有用户工资信息
    List<UserSalary> getUserSalary(int page, int size);

    //增加员工工资信息
    Boolean addUserSalary(UserSalary userSalary);

    //删除员工工资信息
    Boolean deleteUserSalary(int id);

    //修改员工工资信息
    Boolean updateUserSalary(UserSalary userSalary);

    //通过id查询员工工资信息
    UserSalary getUserSalaryById(int id);

    //通过用户名查询员工工资信息
    List<UserSalary> getUserSalaryByUsername(String username);
}
