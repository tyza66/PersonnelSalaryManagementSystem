package com.tyza66.personnelsalarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: tyza66
 * Date: 2023/8/10 18:07
 * Github: https://github.com/tyza66
 **/
@Controller
@RequestMapping("/pure")
public class PureJspRouterController {

    @GetMapping("/temp")
    public String temp(HttpServletRequest request) {
        request.setAttribute("info", "tyza66");
        return "pure/temp";
    }
}
