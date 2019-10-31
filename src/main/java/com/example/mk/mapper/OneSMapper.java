package com.example.mk.mapper;

import com.example.mk.bean.OneS;
import com.example.mk.bean.OneSExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OneSMapper {
    int countByExample(OneSExample example);

    int deleteByExample(OneSExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OneS record);

    int insertSelective(OneS record);

    List<OneS> selectByExample(OneSExample example);

    OneS selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OneS record, @Param("example") OneSExample example);

    int updateByExample(@Param("record") OneS record, @Param("example") OneSExample example);

    int updateByPrimaryKeySelective(OneS record);

    int updateByPrimaryKey(OneS record);
}