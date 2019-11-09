package com.example.mk.controller;

import com.alibaba.fastjson.JSON;
import com.example.mk.bean.OneA;
import com.example.mk.service.OneAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("onea")
public class OneAController {

    @Autowired
    private OneAService oneAService;

    @RequestMapping("/query_oneA")
    @ResponseBody
    public String query_oneA(){


        List<OneA> OneAs = oneAService.queryOneA();
        
       log.debug("OneAController-/onea/query_oneA--中的oneAs集合---{}",OneAs);
       
        return JSON.toJSONString(OneAs);
    }
}
