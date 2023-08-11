package com.tyza66.personnelsalarymanagement.controller;

import cn.hutool.crypto.SmUtil;
import com.tyza66.personnelsalarymanagement.pojo.UserSalary;
import com.tyza66.personnelsalarymanagement.service.AdminService;
import com.tyza66.personnelsalarymanagement.service.UserSalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/8/10 18:07
 * Github: https://github.com/tyza66
 **/

@Slf4j
@Controller
@RequestMapping("/pure")
public class PureJspRouterController {
    //要求必须使用纯jsp实现管理薪资系统 并且要在系统中使用国密234
    //但是还是因为使用了springboot框架 所以还是有一些不纯的地方 比如先前引入的依赖和配置对Servlet不太友好
    //干脆JQ也不使用 直接纯jsp
    //我的思路是这样的
    //为了防止页面跳转 我这里所有get请求都使用自跳转的模式
    //post请求都使用表单提交的模式直接可以重定向回原来页面（不重定向的话刷新的时候会再次提交post信息，重定向会回到原get请求的界面）
    //每个页面对应一组get和post请求 (每对控制器其实相当于一个Servlet的doService方法)

    //相关依赖
    //用户服务
    private AdminService pureAdminService;

    //人员薪资服务
    private UserSalaryService pureUserSalaryImpl;

    //依赖注入
    //注入用户服务
    @Autowired
    @Qualifier("pureAdminServiceImpl")
    public void setPureAdminService(AdminService pureAdminService) {
        this.pureAdminService = pureAdminService;
    }

    //注入人员薪资服务
    @Autowired
    @Qualifier("pureUserSalaryImpl")
    public void setPureUserSalaryImpl(UserSalaryService pureUserSalaryImpl) {
        this.pureUserSalaryImpl = pureUserSalaryImpl;
    }


    //先试一下方案是否可行
    //测试用页面 通过自跳转带参分情况实现同一界面的不同显示
    @GetMapping("/temp")
    public String temp(HttpServletRequest request, @RequestParam(required = false, defaultValue = "") String info) {
        if (info == null || info.equals("")) {
            //如果什么也没传过来 就显示默认的
            request.setAttribute("info", "tyza66");
        } else {
            //如果传过来了 就显示传过来的
            request.setAttribute("info", info);
        }
        return "pure/temp";
    }

    //测试用post请求
    //这里使用重定向回有自定义参数渲染的页面 并且返回界面是可以带参的
    @PostMapping("/temp")
    public String testPost(@RequestParam String username) {
        log.info("调用了测试提交post，username:{}", username);
        //将用户输入在输入框中的值重定向传回去
        return "redirect:/pure/temp?info=" + username;
    }
    //测试结果 方案可行 俩控制器可以对等相当于一个Servlet的doService方法
    //但是大量的String比较确实会影响性能 有待优化
    //并且过长的url也不太好看 甚至有可能超出长度限制 有待优化

    //登录的Get
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "pure/login";
    }

    //登录的Post
    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, HttpSession session, @RequestParam String username, @RequestParam String password) {
        log.info("调用了登录post，username:{},password:{}", username, password);
        //里面使用了使用国密2
        if (pureAdminService.login(username, password)) {
            //登录成功
            session.setAttribute("info", "登录成功,一秒后跳转到管理界面");
            log.info("登录成功,一秒后跳转到管理界面");
            session.setAttribute("logined", "1");
            session.setAttribute("username", username);
            return "redirect:/pure/login";
        } else {
            //登录失败
            session.setAttribute("info", "登录失败,密码错误");
            log.info("登录失败,密码错误");
            session.setAttribute("logined", "0");
            return "redirect:/pure/login";
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpSession session) {
        session.setAttribute("info", "退出成功,一秒后跳转到登陆界面");
        log.info("退出成功,一秒后跳转到登陆界面");
        session.setAttribute("username", null);
    }

    //注册的Get
    @GetMapping("/register")
    public String register(HttpServletRequest request) {
        return "pure/register";
    }

    //注册的Post
    @PostMapping("/register")
    public String registerPost(HttpServletRequest request, HttpSession session, @RequestParam String username, @RequestParam String password) {
        log.info("调用了注册post，username:{},password:{}", username, password);
        //里面使用了国密2
        if (pureAdminService.register(username, password)) {
            //注册成功
            session.setAttribute("info", "注册成功,一秒后跳转到登陆界面");
            log.info("注册成功,一秒后跳转到登陆界面");
            session.setAttribute("success", "1");
            return "redirect:/pure/register";
        } else {
            //注册失败
            session.setAttribute("info", "注册失败，用户名已存在，请重新注册");
            log.info("注册失败，用户名已存在，请重新注册");
            session.setAttribute("success", "0");
            return "redirect:/pure/register";
        }
    }

    //国密在这里的用法我将模仿之前文档中的密码机中的用法
    //在密码机中使用的模式是类似设计模式中的过滤器模式的一种模式
    //数据在用户和程序直接有一层过滤（加密解密） 数据在程序和数据库之间也有一层过滤（加密解密）
    //这样做的结果就是能够精确控制安全细节 把控每个细节 控制某个环节该不该使用密文
    //在薪资管理系统中 我将唯一管理的一张表假设为敏感信息，这个信息中的薪资信息完全当作极端敏感信息
    //国密在系统中的体现于 整个薪资表除了id和薪资以外的信息都进行m4加密处理，其中薪资信息使用m2加密处理保存
    //并且密钥对是和姓名绑定的 用户进行检索的时候必须先验证姓名和输入的私钥是否匹配

    //管理的Get
    @GetMapping("/manage")
    public String manage(HttpServletRequest request, HttpSession session, @RequestParam(required = false, defaultValue = "") String key, @RequestParam(required = false, defaultValue = "") String mode) {
        //检查当前的登陆状态 如果没登陆 在界面中会处理如果登录了 开始拉取表单信息
        //所有管理的操作都是在已经登陆的前提下
        if (session.getAttribute("username") != null) {
            //如果没有传过来key 默认显示全部表单信息
            if (key == null || key.equals("")) {
                List<UserSalary> userSalary = pureUserSalaryImpl.getUserSalary();
                session.setAttribute("userSalary", userSalary);
            } else {
                //如果传过来了 就显示传过来的
                //如果当前的mode是1 那么执行搜索操作
                if (mode != null && mode.equals("1")) {
                    UserSalary userSalaryById = pureUserSalaryImpl.getUserSalaryById(Integer.parseInt(key));
                    ArrayList<UserSalary> userSalaries = new ArrayList<>();
                    userSalaries.add(userSalaryById);
                    session.setAttribute("userSalary", userSalaries);
                }
                //如果当前的mode是2 那么执行删除操作 并且显示出来删除后的结果
                //这里这个逻辑写的有点拉跨 跟其他情况组合起来其实可以更简便
                //先这样吧
                if (mode != null && mode.equals("2")) {
                    Boolean aBoolean = pureUserSalaryImpl.deleteUserSalary(Integer.parseInt(key));
                    if (aBoolean){
                        session.setAttribute("info", "删除成功");
                        session.setAttribute("delete","1");
                    }else{
                        session.setAttribute("info", "删除失败");
                        session.setAttribute("delete","0");
                    }
                    List<UserSalary> userSalary = pureUserSalaryImpl.getUserSalary();
                    session.setAttribute("userSalary", userSalary);
                }
            }
        }
        return "pure/manage";
    }

    //管理的Post
    @PostMapping("/manage")
    public String managePost(HttpServletRequest request, HttpSession session,
                             @RequestParam(required = false, defaultValue = "") String mode,
                             @RequestParam(required = false, defaultValue = "") String xm,
                             @RequestParam(required = false, defaultValue = "") String nrq,
                             @RequestParam(required = false, defaultValue = "") String yrq,
                             @RequestParam(required = false, defaultValue = "") String xz) {
        log.info("调用了管理post，mode={}", mode);
        //所有的管理操作都是在已经登录的前提下
        if (session.getAttribute("username") != null) {
            if ((mode != null && mode.equals("1")) && (xm != null && !xm.equals("") && (nrq != null && !nrq.equals("")) && (yrq != null && !yrq.equals("")) && (xz != null && !xz.equals("")))) {
                //如果传过来了完整要插入的值 就直接插入
                Boolean aBoolean = pureUserSalaryImpl.addUserSalary(new UserSalary(0, xm, new BigDecimal(xz), nrq, yrq));
                if (aBoolean) {
                    session.setAttribute("info", "添加成功");
                    session.setAttribute("add", "1");
                    log.info("添加成功");
                } else {
                    session.setAttribute("info", "添加失败");
                    session.setAttribute("add", "0");
                    log.info("添加失败");
                }
            }
        } else {
            session.setAttribute("info", "请先登录");
            session.setAttribute("add", "0");
            log.info("请先登录");
        }
        return "redirect:/pure/manage";
    }
}
