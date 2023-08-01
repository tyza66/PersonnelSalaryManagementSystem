package com.tyza66.personnelsalarymanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.personnelsalarymanagement.mapper.UserSalaryMapper;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.service.UserSalaryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/1 13:12
 * Github: https://github.com/tyza66
 **/

@Service
public class UserSalaryServiceImpl extends ServiceImpl<UserSalaryMapper, UserSalary> implements UserSalaryService {

    @Override
    public List<UserSalary> getUserSalary(int page, int size) {
        QueryWrapper<UserSalary> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("name");
        queryWrapper.orderByDesc("year");
        queryWrapper.orderByDesc("month");
        IPage<UserSalary> end = baseMapper.selectPage(new Page<>(page, size), queryWrapper);
        return end.getRecords();
    }

    @Override
    public int getPages(int page, int size) {
        QueryWrapper<UserSalary> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("name");
        queryWrapper.orderByDesc("year");
        queryWrapper.orderByDesc("month");
        IPage<UserSalary> end = baseMapper.selectPage(new Page<>(page, size), queryWrapper);
        return (int) end.getPages();
    }

    @Override
    public Boolean addUserSalary(UserSalary userSalary) {
        userSalary.setId(0);
        return baseMapper.insert(userSalary)>=1;
    }

    @Override
    public Boolean deleteUserSalary(int id) {
        return baseMapper.deleteById(id)>=1;
    }

    @Override
    public Boolean updateUserSalary(UserSalary userSalary) {
        return baseMapper.updateById(userSalary)>=1;
    }

    @Override
    public UserSalary getUserSalaryById(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<UserSalary> getUserSalaryByUsername(String username) {
        QueryWrapper<UserSalary> userSalaryQueryWrapper = new QueryWrapper<>();
        userSalaryQueryWrapper.like("name", username);
        return baseMapper.selectList(userSalaryQueryWrapper);
    }

}
