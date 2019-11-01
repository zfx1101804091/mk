package com.example.mk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//跳转的界面  http://localhost:8080/login
public class Pagecontroller {

    @RequestMapping("/index")
    public String index(){

        return "index";
    }



    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/login_fail")
    public String login_fail(){

        return "404";
    }
}
