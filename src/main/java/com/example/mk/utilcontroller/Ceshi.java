package com.example.mk.utilcontroller;

import java.util.Date;

/**
 * String.format()的详细用法
 * url：https://blog.csdn.net/anita9999/article/details/82346552
 */
public class Ceshi {


    public static void main(String[] args) {
        System.out.println(String.format("%07d" , 7));

// Output: 0000007

        String str=String.format("Hi,%s %s %s", "小超","是个","大帅哥");
        System.out.println(str);


        Date date=new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n",date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",date);
    }
}
