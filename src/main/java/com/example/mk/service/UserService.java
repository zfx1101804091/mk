package com.example.mk.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:34
 */
public interface UserService {
    User queryName(String login_name, String password,String code);

    int insertLoginMsg(JSONObject jsonObject, int status);
}
