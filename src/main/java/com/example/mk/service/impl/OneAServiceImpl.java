package com.example.mk.service.impl;

import com.example.mk.mapper.OneAMapper;
import com.example.mk.service.OneAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OneAServiceImpl implements OneAService {

    @Autowired
    private OneAMapper oneAMapper;

    @Override
    public List<Map<String, String>> queryOneA() {

        List<Map<String,String>> list = oneAMapper.queryOneA();
        return list;
    }
}
