package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import com.example.mk.common.utils.CommonUtils;
import com.example.mk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:25
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response,User user) {
        /*String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");*/

        String login_name = user.getLogin_name();
        String password = user.getPassword();
        String code = request.getParameter("code");
        User users = userService.queryName(login_name, password, code);
        
        int status =0;//登陆状态 0未登录成功；1登陆成功
        int flag = 0;
        JSONObject loginMsg = null;
        
        try {
            loginMsg = CommonUtils.getLoginMsg(request);
            if (users!=null){
                //登陆信息存库
                status = 1;
                flag = userService.insertLoginMsg(loginMsg,status);
                log.debug("登陆成功---{}",JSONObject.toJSONString(users));
                
            }
            return "redirect:/admin";
        } catch (Exception e) {
        log.debug("登陆失败---{}",JSONObject.toJSONString(users));
        log.debug("loginMsg--{}",loginMsg);
        flag = userService.insertLoginMsg(loginMsg,status);
        return "redirect:/login_fail";
        }
        
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(){

        List<User> userList = userService.list();
        String data = JSON.toJSONString(userList);
        log.debug(data);
        return data;
    }

    @RequestMapping("/title")
    @ResponseBody
    public String title(){
         /*
    * 
    * CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `login_name` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `phone` varchar(60) DEFAULT NULL,
  `code` varchar(6) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `editflag` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

    * */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","id");
        jsonObject.put("role_id","角色ID");
        jsonObject.put("login_name","登陆名");
        jsonObject.put("name","昵称");
        jsonObject.put("password","密码");
        jsonObject.put("state","状态");
        jsonObject.put("phone","联系方式");
        jsonObject.put("operator_id","操作员ID");
        jsonObject.put("editflag","编辑时间");
        return "{id,角色ID,登陆名,昵称,密码,状态,联系方式,操作员ID,编辑时间}";
    }
}
