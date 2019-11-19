package com.example.mk.bean.treedata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class TreeData implements Serializable {
    private int id;
    private String text;
    private String iconCls;
    private List<TreeChildren> children;
}
