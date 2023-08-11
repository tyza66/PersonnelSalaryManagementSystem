package com.tyza66.personnelsalarymanagement.service.pureimpl;

import cn.hutool.crypto.SmUtil;
import com.tyza66.personnelsalarymanagement.mapper.PureAdminMapper;
import com.tyza66.personnelsalarymanagement.pojo.Admin;
import com.tyza66.personnelsalarymanagement.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: tyza66
 * Date: 2023/8/11 8:10
 * Github: https://github.com/tyza66
 **/

@Service
public class PureAdminServiceImpl implements AdminService {

    @Resource
    private PureAdminMapper pureAdminMapper;

    //登录
    @Override
    public Boolean login(String username, String password) {
        //使用国密sm3加密验证
        password = SmUtil.sm3().digestHex(password);
        //其实加密应该写在这里面 为了方便您的查看和测试 我就写在控制器层了
        Admin login = pureAdminMapper.login(username, password);
        if (login != null) {
            return true;
        }else{
            return false;
        }
    }

    //注册
    @Override
    public Boolean register(String username, String password) {
        //如果用户已经存在则返回false
        if(pureAdminMapper.selectUser(username).size()>=1){
            return false;
        }
        //使用国密sm3加密注册
        password = SmUtil.sm3().digestHex(password);
        int register = pureAdminMapper.register(username, password);
        if (register >= 1) {
            return true;
        }else{
            return false;
        }
    }
}
