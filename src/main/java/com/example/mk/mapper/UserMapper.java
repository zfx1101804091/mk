package com.example.mk.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User queryUser(@Param("login_name")String login_name, @Param("password")String password,@Param("code") String code );

    int insertLoginMsg(Map map);
}
