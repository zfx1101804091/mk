package com.example.mk.bean.treedata;

import com.example.mk.dto.TreeVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class TreeData extends TreeVO {
    
    private List<TreeChildren> children;
}
