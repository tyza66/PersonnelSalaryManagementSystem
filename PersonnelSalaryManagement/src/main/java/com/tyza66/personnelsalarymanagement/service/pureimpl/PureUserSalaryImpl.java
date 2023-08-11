package com.tyza66.personnelsalarymanagement.service.pureimpl;

import com.tyza66.personnelsalarymanagement.mapper.PureSalaryMapper;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.service.UserSalaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/11 11:52
 * Github: https://github.com/tyza66
 **/

public class PureUserSalaryImpl implements UserSalaryService {

    @Autowired
    private PureSalaryMapper pureSalaryMapper;
    @Override
    public List<UserSalary> getUserSalary(int page, int size) {
        pureSalaryMapper.selectAll();
        
    }

    @Override
    public int getPages(int page, int size) {
        return 0;
    }

    @Override
    public Boolean addUserSalary(UserSalary userSalary) {
        return null;
    }

    @Override
    public Boolean deleteUserSalary(int id) {
        return null;
    }

    @Override
    public Boolean updateUserSalary(UserSalary userSalary) {
        return null;
    }

    @Override
    public UserSalary getUserSalaryById(int id) {
        return null;
    }

    @Override
    public List<UserSalary> getUserSalaryByUsername(String username) {
        return null;
    }

    @Override
    public BigDecimal getSumSalaryByUsername(String username) {
        return null;
    }

    @Override
    public BigDecimal getSumSalaryByUsernameAndMonth(String username, Integer month) {
        return null;
    }

    @Override
    public BigDecimal getSumSalaryByUsernameAndYear(String username, Integer year) {
        return null;
    }

    @Override
    public BigDecimal getSalaryByUsernameAndYearAndMonth(String username, Integer year, Integer month) {
        return null;
    }
}
