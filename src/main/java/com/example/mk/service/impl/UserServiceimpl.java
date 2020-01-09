package com.example.mk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import com.example.mk.mapper.UserMapper;
import com.example.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:35
 */
@Service
public class UserServiceimpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryName( String login_name, String password,String code) {
        
        User users = userMapper.queryUser(login_name,password,code);
        return users;
    }


    @Override
    public int insertLoginMsg(JSONObject jsonObject, int status) {

        jsonObject.put("status", String.valueOf(status));

        //json转map
        Map map = JSON.parseObject(JSON.toJSONString(jsonObject), Map.class);

        return userMapper.insertLoginMsg(map);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
