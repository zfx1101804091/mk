package com.example.mk.service.impl;

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
    public User queryName(String username, String login_name, String password) {
        
        User users = userMapper.queryUser(username,login_name,password);
        return users;
    }
}