package com.example.mk.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/9 0009 14:44
 */
public class CommonUtils {

    /** 判断是否是IE */
    public static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;
    }

    /**
     * 获取IE版本 
     *
     * @param request
     * @return
     */
    public static Double getIEversion(HttpServletRequest request) {
        Double version = 0.0;
        if (getBrowserType(request, "msie 10.0")) {
            version = 10.0;
        }
        if (getBrowserType(request, "msie 9.0")) {
            version = 9.0;
        }
        if (getBrowserType(request, "msie 8.0")) {
            version = 8.0;
        }
        if (getBrowserType(request, "msie 7.0")) {
            version = 7.0;
        }
        if (getBrowserType(request, "msie 6.0")) {
            version = 6.0;
        }
        return version;
    }

    /**
     * 获取浏览器类型 
     *
     * @param request
     * @return
     */
    public static BrowserType getBrowserType(HttpServletRequest request) {
        BrowserType browserType = null;
        if (getBrowserType(request, "msie 10.0")) {
            browserType = BrowserType.IE9;
        }
        if (getBrowserType(request, "msie 9.0")) {
            browserType = BrowserType.IE9;
        }
        if (getBrowserType(request, "msie 8.0")) {
            browserType = BrowserType.IE8;
        }
        if (getBrowserType(request, "msie 7.0")) {
            browserType = BrowserType.IE7;
        }
        if (getBrowserType(request, "msie 6.0")) {
            browserType = BrowserType.IE6;
        }
        if (getBrowserType(request, "Firefox")) {
            browserType = BrowserType.Firefox;
        }
        if (getBrowserType(request, "Safari")) {
            browserType = BrowserType.Safari;
        }
        if (getBrowserType(request, "Chrome")) {
            browserType = BrowserType.Chrome;
        }
        if (getBrowserType(request, "Opera")) {
            browserType = BrowserType.Opera;
        }
        if (getBrowserType(request, "Camino")) {
            browserType = BrowserType.Camino;
        }
        return browserType;
    }

    private static boolean getBrowserType(HttpServletRequest request,
                                          String brosertype) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf(brosertype) > 0 ? true : false;
    }

    private final static String IE10 = "MSIE 10.0";
    private final static String IE9 = "MSIE 9.0";
    private final static String IE8 = "MSIE 8.0";
    private final static String IE7 = "MSIE 7.0";
    private final static String IE6 = "MSIE 6.0";
    private final static String MAXTHON = "Maxthon";
    private final static String QQ = "QQBrowser";
    private final static String GREEN = "GreenBrowser";
    private final static String SE360 = "360SE";
    private final static String FIREFOX = "Firefox";
    private final static String OPERA = "Opera";
    private final static String CHROME = "Chrome";
    private final static String SAFARI = "Safari";
    private final static String OTHER = "其它";

    public static String checkBrowse(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        if (regex(OPERA, userAgent))
            return OPERA;
        if (regex(CHROME, userAgent))
            return CHROME;
        if (regex(FIREFOX, userAgent))
            return FIREFOX;
        if (regex(SAFARI, userAgent))
            return SAFARI;
        if (regex(SE360, userAgent))
            return SE360;
        if (regex(GREEN, userAgent))
            return GREEN;
        if (regex(QQ, userAgent))
            return QQ;
        if (regex(MAXTHON, userAgent))
            return MAXTHON;
        if (regex(IE10, userAgent))
            return IE10;
        if (regex(IE9, userAgent))
            return IE9;
        if (regex(IE8, userAgent))
            return IE8;
        if (regex(IE7, userAgent))
            return IE7;
        if (regex(IE6, userAgent))
            return IE6;
        return OTHER;
    }

    public static boolean regex(String regex, String str) {
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(str);
        return m.find();
    }
    
    
    /*######################################################################*/
    public static String getLoginMsg(HttpServletRequest req) {

        //获取登陆设备ip
        String ip = req.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }

        //获取操作系统
        String operation = System.getProperty("os.name");

        //获取浏览器信息
        //String browser = req.getHeader("User-Agent");
        String browser = CommonUtils.checkBrowse(req);

        //获取当前时间
        String date1 = DateUtil.getNowDate();

        JSONObject msg=new JSONObject();
        msg.put("ip",ip);
        msg.put("operation",operation);
        msg.put("browser",browser);
        msg.put("editime",date1);

        return msg.toJSONString();
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
