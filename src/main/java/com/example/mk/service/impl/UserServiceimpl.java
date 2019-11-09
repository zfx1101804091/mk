package com.example.mk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import com.example.mk.mapper.UserMapper;
import com.example.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int insertLoginMsg(String jsonObject) {
        JSONObject json = null;
        int flag=0;
        
        try {
            json = JSONObject.parseObject(jsonObject);
            String ip=json.getString("ip");
            String operation = json.getString("operation");
            String browser =  json.getString("browser");
            String editime = json.getString("editime");
            
             flag =  userMapper.insertLoginMsg(ip,operation,browser,editime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
        return flag;
    }
}
