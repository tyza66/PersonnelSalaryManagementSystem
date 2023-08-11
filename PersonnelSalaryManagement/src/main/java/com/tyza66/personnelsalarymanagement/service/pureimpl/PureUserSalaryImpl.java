package com.tyza66.personnelsalarymanagement.service.pureimpl;

import com.tyza66.personnelsalarymanagement.mapper.PureSalaryMapper;
import com.tyza66.personnelsalarymanagement.mapper.UserKeyMapper;
import com.tyza66.personnelsalarymanagement.pojo.UserKeys;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.pojo.UserSalaryEncryption;
import com.tyza66.personnelsalarymanagement.service.UserSalaryService;
import com.tyza66.personnelsalarymanagement.util.UserSalaryEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/11 11:52
 * Github: https://github.com/tyza66
 **/

@Service
public class PureUserSalaryImpl implements UserSalaryService {

    @Resource
    private PureSalaryMapper pureSalaryMapper;

    @Resource
    private UserKeyMapper userKeyMapper;

    //这里是管理员获得所有用户的工资信息 直接使用工具类将全部查到的工资信息全部解解密返回
    @Override
    public List<UserSalary> getUserSalary() {
        List<UserSalaryEncryption> userSalaryEncryptions = pureSalaryMapper.selectAll();
        //如果是在正常使用中可能会有一个用户一个自己的keys的情况 这个演示项目中我就都使用公用的keys了
        UserKeys publicKeys = userKeyMapper.getByName("公共用");
        return UserSalaryEncryptionUtil.decrypt(publicKeys.getPrivatekey(), userSalaryEncryptions);
    }


    @Override
    public List<UserSalary> getUserSalary(int page, int size) {
        return null;
    }

    @Override
    public int getPages(int page, int size) {
        return 0;
    }

    @Override
    public Boolean addUserSalary(UserSalary userSalary) {
        List<UserSalaryEncryption> userSalaryEncryptions = pureSalaryMapper.selectAll();
        //如果是在正常使用中可能会有一个用户一个自己的keys的情况 这个演示项目中我就都使用公用的keys了
        UserKeys publicKeys = userKeyMapper.getByName("公共用");
        //先加密
        UserSalaryEncryption userSalaryEncryption = UserSalaryEncryptionUtil.encrypt(publicKeys.getPublickey(), userSalary);
        //然后插入
        return pureSalaryMapper.insert(userSalaryEncryption) > 0;
    }

    @Override
    public Boolean deleteUserSalary(int id) {
        return pureSalaryMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateUserSalary(UserSalary userSalary) {
        //首先将传过来的信息加密
        UserSalaryEncryption infoById = UserSalaryEncryptionUtil.encrypt(userKeyMapper.getByName("公共用").getPublickey(), userSalary);
        //然后更新
        return pureSalaryMapper.updateById(infoById) > 0;
    }

    @Override
    public UserSalary getUserSalaryById(int id) {
        //先按条件查出来那一条
        List<UserSalaryEncryption> userSalaryEncryptions = pureSalaryMapper.selectById(id);
        //获得第一条
        UserSalaryEncryption userSalaryEncryption = userSalaryEncryptions.get(0);
        //将数据解密
        UserKeys publicKeys = userKeyMapper.getByName("公共用");
        //将数据解密返回
        return UserSalaryEncryptionUtil.decrypt(publicKeys.getPrivatekey(), userSalaryEncryption);
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
