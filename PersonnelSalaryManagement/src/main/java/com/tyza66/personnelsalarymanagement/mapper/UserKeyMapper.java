package com.tyza66.personnelsalarymanagement.mapper;

import com.tyza66.personnelsalarymanagement.pojo.UserKeys;
import org.apache.ibatis.annotations.Select;

/**
 * Author: tyza66
 * Date: 2023/8/11 12:33
 * Github: https://github.com/tyza66
 **/

public interface UserKeyMapper {

    @Select("select * from user_keys where name=#{name}")
    UserKeys getByName(String name);

}
