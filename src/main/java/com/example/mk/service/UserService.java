package com.example.mk.service;

import com.example.mk.bean.User;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:34
 */
public interface UserService {
    User queryName(String username, String login_name, String password);
}