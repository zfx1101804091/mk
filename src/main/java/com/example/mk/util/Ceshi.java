package com.example.mk.util;

import com.alibaba.fastjson.JSONArray;

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

      /*  String str = String.format("Hi,%s %s %s", "小超", "是个", "大帅哥");
        System.out.println(str);


        Date date = new Date();
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
        System.out.println("msg--"+msg);*/


        List<String> ipList = getLocalIPList();
        for (String s : ipList) {
            System.out.println(s);
        }

    }

    /**
     * IceWee 2013.07.19
     * 获取本地IP列表（针对多网卡情况）
     *
     * @return
     */
    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }
}
