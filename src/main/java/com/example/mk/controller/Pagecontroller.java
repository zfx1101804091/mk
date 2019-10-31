package com.example.mk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//跳转的界面  http://localhost:8080/login
public class Pagecontroller {

    @RequestMapping("/login")
    public String page1(){

        return "index";
    }
}
