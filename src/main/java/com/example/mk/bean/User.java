package com.example.mk.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/1 0001 22:40
 */
@Data
public class User implements Serializable {
    private int id;
    private int role_id;
    private String login_name;
    private String name;
    private String password;
    private int state;
    private String phone;
    private int operator_id;
    private String editflag;

    
        
    /*
    * 
    * CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `login_name` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `phone` varchar(60) DEFAULT NULL,
  `code` varchar(6) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `editflag` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

    * */
}
