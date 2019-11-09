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
        //登陆信息存库
        String loginMsg = getLoginMsg(request);
        int flag = userService.insertLoginMsg(loginMsg);
        if (flag!=1){
            try {
                response.getWriter().write("登陆信息存储异常！");
            } catch (IOException e) {
                log.error("登陆日志存储异常---{}",e.getMessage());
            }
        }else {
            log.debug("登陆日志存储成功---{}",loginMsg);
        }
        
        log.debug("查询登陆用户信息---{}", JSON.toJSONString(users));
        
        if (users != null) {
            return "redirect:/index ";
        }
        return "redirect:/login_fail ";
    }

    public String getLoginMsg(HttpServletRequest req) {

        //获取登陆设备ip
        String ip = req.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }

        //获取操作系统
        String operation = System.getProperty("os.name");

        //获取浏览器信息
        //String browser = req.getHeader("User-Agent");
        String browser = CommonUtils.checkBrowse(req);

        //获取当前时间
        String date1 = DateUtil.getNowDate();

        JSONObject msg=new JSONObject();
        msg.put("ip",ip);
        msg.put("operation",operation);
        msg.put("browser",browser);
        msg.put("editime",date1);

        return msg.toJSONString();
    }

}
