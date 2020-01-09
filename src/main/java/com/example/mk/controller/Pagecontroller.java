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


    @RequestMapping("/ztree")
    public String ztree(){

        return "backstage";
    }

    @RequestMapping("/operator")
    public String operator(){

        return "/user/operator";
    }

    @RequestMapping("/demo")
    public String demo(){

        return "/user/demo";
    }

    @RequestMapping("/demo2")
    public String demo2(){

        return "/user/demo2";
    }

    @RequestMapping("/demo3")
    public String demo3(){

        return "/user/demo3";
    }
    @RequestMapping("/timeshow")
    public String timeshow(){

        return "/user/timeshow";
    }

    @RequestMapping("/admin")
    public String admin(){

        return "/user/admin";
    }
    @RequestMapping("/main")
    public String main(){

        return "/user/main";
    }

    @RequestMapping("/lay-index")
    public String layindex(){

        return "/layuidemo/index";
    }

    @RequestMapping("/user_list")
    public String user_list(){

        return "/user/user_list";
    }

    @RequestMapping("/showMap")
    public String showMap(){

        return "/mymap/mmap";
    }
}
