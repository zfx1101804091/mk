package com.example.mk.service.impl;

import com.example.mk.bean.OneA;
import com.example.mk.mapper.OneAMapper;
import com.example.mk.service.OneAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneAServiceImpl implements OneAService {

    @Autowired
    private OneAMapper oneAMapper;


    @Override
    public OneA queryOneA() {
        return null;
    }

}
