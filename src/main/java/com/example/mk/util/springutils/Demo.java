package com.example.mk.util.springutils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo {

    /**
     * 导出原始记录信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/statistics_export_originPassRec.do", method= RequestMethod.GET, produces="text/html; charset=utf-8")
    public String exportOriginPassRec(HttpServletRequest request, HttpServletResponse response) {
        /*try {
            String title="原始记录相关数据";

            //获取参数
            String parkid=request.getParameter("parkid");//区域ID
            String passageid=request.getParameter("passageid");//通道ID
            String cartypeid=request.getParameter("cartypeid");//车辆类型
            String begin=request.getParameter("begin");//
            String end=request.getParameter("end");//
            String plate=request.getParameter("plate");	//车牌
            String parkName=request.getParameter("parkName");	//区域名称
            String passageName=request.getParameter("passageName");	//通道名称
            String cartypeName=request.getParameter("cartypeName");	//车辆类型名称
            String department=request.getParameter("department");	//部门

            Integer pageS=1000000000;
            Integer pageN=0;

            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
                title = java.net.URLEncoder.encode(title, "UTF-8");
            } else {
                //非IE浏览器的处理：
                title = new String(title.getBytes("UTF-8"),"ISO-8859-1");
            }

            request.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", title+".xls"));
            response.setContentType("octet-stream;charset=utf-8");
            response.setCharacterEncoding("UTF-8");


            HashMap<String, Object> map=new HashMap<String, Object>();
            JSONObject job = new JSONObject();
            job.put("park_id", parkid);
            job.put("passage_id", passageid);
            job.put("car_type_id", cartypeid);
            job.put("plate", plate);

            job.put("begin", begin);
            job.put("end", end);
            job.put("department", department);
            job.put("per_count", pageS);
            job.put("position", pageN);
            map.put("json", job.toString());
            //获取数据
            JSONArray jd = new JSONArray();
            try {
                jd = JSONArray.fromObject(oprs.getOriginPassRec(map));
            } catch (Exception e) {
                e.printStackTrace();
            }

            String[] hd=new String[2];
            hd[0]="plate,name,department,doTime,passageName,passageState,imageName,memo";
            hd[1]="车牌,车主分组,部门,入场时间,通道名称,通道状态,文件名称,备注";

            JSONArray rjd=new JSONArray();
            JSONObject jo=new JSONObject();
            String[] h0=hd[0].split(",");
            String[] h1=hd[1].split(",");
            for(int i=0;i<h0.length;i++){
                jo.put(h0[i], h1[i]);
            }

            rjd.add(jo);
            rjd.addAll(jd);

            String[] headers=hd[0].split(",");
            ExportExcelJson ej=new ExportExcelJson();

            Map<String, String> queryMap = new LinkedHashMap<>();
            queryMap.put("开始时间",begin);
            queryMap.put("结束时间",end);
            queryMap.put("车辆类型",cartypeName);
            queryMap.put("通道",passageName);
            queryMap.put("区域",parkName);
            queryMap.put("车牌",plate);
            try {
                OutputStream out = response.getOutputStream();
                ej.exportExcel(headers, rjd, out, title, queryMap);
                out.close();

            }catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }*/
        return null;
    }
}
