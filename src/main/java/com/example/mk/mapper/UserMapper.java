package com.example.mk.mapper;

import com.example.mk.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User queryUser(@Param("username") String username, @Param("login_name")String login_name, @Param("password")String password);
}
