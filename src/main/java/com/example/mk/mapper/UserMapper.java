package com.example.mk.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.mk.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User queryUser(@Param("login_name")String login_name, @Param("password")String password,@Param("code") String code );

    @Select("select * from user")
    List<User> list();
    
    int insertLoginMsg(Map map);
}
