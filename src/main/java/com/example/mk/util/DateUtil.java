package com.example.mk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/9 0009 13:59
 */
public class DateUtil {
    
    public static String getNowDate() {
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }
}
