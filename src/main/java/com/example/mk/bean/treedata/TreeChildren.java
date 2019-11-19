package com.example.mk.bean.treedata;

import lombok.Data;

import java.io.Serializable;

@Data
public class TreeChildren implements Serializable {
    private int id;
    private String text;
    private String iconCls;
    private TreeAttributes attributes;
}
