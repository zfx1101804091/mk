package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import com.example.mk.bean.treedata.TreeAttributes;
import com.example.mk.bean.treedata.TreeChildren;
import com.example.mk.bean.treedata.TreeData;
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
import java.util.*;

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
    public String login(HttpServletRequest request, HttpServletResponse response,User user) {
        /*String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");*/

        String login_name = user.getLogin_name();
        String password = user.getPassword();
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


    /**
     * 动态创建ztree树，并绑定url跳转
     * @return
     */
    @RequestMapping("getTreedata")
    @ResponseBody
    public String treedata(){

        List<Map<String,TreeChildren>> list = new ArrayList();
        List<TreeChildren> tcList = new ArrayList<>();
        HashMap<String, TreeData> map1 = new HashMap<>();
        HashMap<String, List<TreeChildren>> map2 = new HashMap<>();

        TreeData treeData1 = new TreeData();
        TreeChildren treeChildren = new TreeChildren();
        TreeChildren treeChildren2 = new TreeChildren();
        TreeAttributes treeAttributes1 = new TreeAttributes();
        TreeAttributes treeAttributes2 = new TreeAttributes();

        treeChildren.setId("11");
        treeChildren.setText("目录1-1");
        treeChildren.setIconCls("icon-page");

        treeAttributes1.setUrl("/demo2");
        treeChildren.setAttributes(treeAttributes1);


        treeChildren2.setId("12");
        treeChildren2.setText("目录1-2");
        treeChildren2.setIconCls("icon-page");
        treeAttributes2.setUrl("/timeshow");
        treeChildren2.setAttributes(treeAttributes2);

        tcList.add(treeChildren);
        tcList.add(treeChildren2);
        //
        log.debug("map2--{}",tcList.toString());

        treeData1.setId("1");
        treeData1.setText("目录1");
        treeData1.setIconCls("icon-page");

        treeData1.setChildren(tcList);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(treeData1);


        log.debug("trdata----{}",jsonArray);

        return jsonArray.toJSONString();
    }

}
