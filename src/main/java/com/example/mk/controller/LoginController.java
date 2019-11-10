package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import com.example.mk.service.UserService;
import com.example.mk.util.BrowserType;
import com.example.mk.util.CommonUtils;
import com.example.mk.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:25
 */
@Slf4j
@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        User users = userService.queryName(login_name, password, code);
        
        int status =0;//登陆状态 0未登录成功；1登陆成功
        
        String loginMsg = CommonUtils.getLoginMsg(request);
        
        if (users!=null){
            //登陆信息存库
            status = 1;
            int flag = userService.insertLoginMsg(loginMsg,status);
            log.debug("登陆成功---{}",JSONObject.toJSONString(users));
           
        }else {
            log.debug("登陆失败---{}",JSONObject.toJSONString(users));
            int flag = userService.insertLoginMsg(loginMsg,status);
        }
        
        if (users != null) {
            return "redirect:/demo ";
        }
        return "redirect:/login_fail ";
    }

    

}
