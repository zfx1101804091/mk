package com.example.mk.utilcontroller;

import com.example.mk.bean.User;
import com.example.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:25
 */

@Controller
@RequestMapping("user")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");
        User users = userService.queryName(username,login_name,password);
        
        if (users!=null){
            return "success";
        }
        return "fail";
    }
}
