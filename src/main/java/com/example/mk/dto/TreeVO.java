package com.example.mk.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: ztree实体公共类
 * @author: zheng-fx
 * @time: 2019/11/19 0019 19:46
 */
@Data
public class TreeVO implements Serializable {
    private String id;
    private String text;
    private String iconCls;
}
