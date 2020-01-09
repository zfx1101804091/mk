package com.example.mk.service;

import com.example.mk.bean.User;

import java.awt.*;
import java.util.ArrayList;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:34
 */
public interface UserService {
    User queryName(String login_name, String password,String code);

    int insertLoginMsg(String jsonObject,int status);

    ArrayList<User> list();
}
