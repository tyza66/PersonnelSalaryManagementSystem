package com.tyza66.personnelsalarymanagement.mapper;

import com.tyza66.personnelsalarymanagement.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/11 8:12
 * Github: https://github.com/tyza66
 **/

public interface PureAdminMapper {

    @Select("SELECT * FROM `admin` where username = #{username} and password = #{password}")
    Admin login(String username, String password);

    @Select("SELECT * FROM `admin` where username = #{username}")
    List<Admin> selectUser(String username);

    @Insert("INSERT INTO `personnel_information_management`.`admin` (`username`, `password`) VALUES (#{username}, #{password})")
    @Transactional
    int register(String username, String password);
}
