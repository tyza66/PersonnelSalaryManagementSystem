package com.tyza66.personnelsalarymanagement.service;

import com.tyza66.personnelsalarymanagement.pojo.UserSalary;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:11
 * Github: https://github.com/tyza66
 **/

public interface UserSalaryService {

    //分页查询所有用户工资信息
    List<UserSalary> getUserSalary(int page, int size);

    //获得页数
    int getPages(int page, int size);

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

    //查找一个姓名下的工资总和
    BigDecimal getSumSalaryByUsername(String username);

    //查找一个姓名在一个月份的工资总和
    BigDecimal getSumSalaryByUsernameAndMonth(String username, Integer month);

    //查找一个姓名在一个年份的工资总和
    BigDecimal getSumSalaryByUsernameAndYear(String username, Integer year);

    //查找一个姓名在某一年某一个月份的工资
    BigDecimal getSalaryByUsernameAndYearAndMonth(String username, Integer year, Integer month);
}
