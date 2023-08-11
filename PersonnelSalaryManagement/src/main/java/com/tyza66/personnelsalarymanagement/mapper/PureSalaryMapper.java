package com.tyza66.personnelsalarymanagement.mapper;

import com.tyza66.personnelsalarymanagement.pojo.UserSalaryEncryption;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/11 8:17
 * Github: https://github.com/tyza66
 **/

public interface PureSalaryMapper{

    List<UserSalaryEncryption> selectAll();

    List<UserSalaryEncryption> selectByName(String name);

    List<UserSalaryEncryption> selectById(int id);
}
