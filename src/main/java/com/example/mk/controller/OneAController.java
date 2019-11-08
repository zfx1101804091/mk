package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.example.mk.bean.OneA;
import com.example.mk.service.OneAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("onea")
public class OneAController {

    @Autowired
    private OneAService oneAService;

    @RequestMapping("/query_oneA")
    @ResponseBody
    public String query_oneA(){


        OneA OneAs = oneAService.queryOneA();

        return JSON.toJSONString(OneAs);
    }
}
