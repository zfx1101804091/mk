package com.example.mk.util;

import com.alibaba.fastjson.JSONArray;
import com.example.mk.common.utils.CommonUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * String.format()的详细用法
 * url：https://blog.csdn.net/anita9999/article/details/82346552
 */
public class Ceshi {


    public static void main(String[] args) {
//        System.out.println(String.format("%07d", 7));

// Output: 0000007

        String str = String.format("Hi,%s %s %s", "小超", "是个", "大帅哥");
        System.out.println(str);


        Date date = new Date();
        String str1 = String.format("%tH:%tM", date,date);
        System.out.println("str1---"+str1);
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n", date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n ", date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", date);


        String ip = "127.0.0.1";
        String browser = "Google";
        String operation = "window10";
        String date1 = DateUtil.getNowDate();
        String msg = "{\"ip\":"+ip+",\"operation\":"+operation+",\"browser\":"+browser+",\"editime\":"+date1+"}";
        System.out.println("msg--"+msg);


        List<String> ipList = CommonUtils.getLocalIPList();
        for (String s : ipList) {
            System.out.println(s);
        }

    }


}
