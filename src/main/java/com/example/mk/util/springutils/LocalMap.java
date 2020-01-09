package com.example.mk.util.springutils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.mk.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/*
    有时我们在做开发的时候需要记录每个任务执行时间，或者记录一段代码执行时间，最简单的方法就是打印当前时间与执行完时间的差值，
    然后这样如果执行大量测试的话就很麻烦，并且不直观，如果想对执行的时间做进一步控制，则需要在程序中很多地方修改，
    目前spring-framework提供了一个StopWatch类可以做类似任务执行时间控制，也就是封装了一个对开始时间，结束时间记录操作的Java类
    --------------------------------------------------------------------------------------------------------------
    start(String taskName)  //计时开始，taskName可选，不填则为""
    stop()  //计时结束
    currentTaskName()     //获取当前任务名称
    getTotalTimeMillis()  //获取总时间
    shortSummary()        //获取概要任务信息
    prettyPrint()         //获取详细信息
 */

@Slf4j
@RequestMapping("getmap")
@RestController
public class LocalMap {

    @RequestMapping("/map")
    public String getmap(HttpServletRequest request) throws IOException {

        String localmap = getLocalmap(request);
        return localmap;
    }


        /**
         * 读取
         *
         * @param rd
         * @return
         * @throws IOException
         */
        private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        /**
         * 创建链接
         *
         * @param url
         * @return
         * @throws IOException
         * @throws JSONException
         */
        private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = JSONObject.parseObject(jsonText);
                return json;
            } finally {
                is.close();
            }
        }


    /**
     * 百度获取城市信息
     *
     * @param
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static String getLocalmap(HttpServletRequest request)  {
        String ip = CommonUtils.getLocalIp(request);
        // 百度地图申请的ak
        String ak = "SIu51KgrZoe31nllgcpXuUZ26UzsRkdX";
        // 这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        JSONObject json = null;
        try {
            json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ip="+ip+"&ak="+ak);

            //这里只取出了两个参数，根据自己需求去获取
            JSONObject obj = (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
            String province = obj.getString("province");
            System.out.println(province);

            JSONObject obj2 = (JSONObject) json.get("content");
            String address = obj2.getString("address");
            return address;
        } catch (IOException e) {
            return "调用百度地图API异常"+e.getMessage();
        }

    }

}
