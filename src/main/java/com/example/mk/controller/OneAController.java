package com.example.mk.controller;

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


        List<Map<String,String>> list = oneAService.queryOneA();

        return list.toString();
    }
}
