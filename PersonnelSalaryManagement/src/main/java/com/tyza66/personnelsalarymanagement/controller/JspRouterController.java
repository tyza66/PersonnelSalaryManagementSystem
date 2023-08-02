package com.tyza66.personnelsalarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: tyza66
 * Date: 2023/8/2 10:57
 * Github: https://github.com/tyza66
 **/

@Controller
public class JspRouterController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}
