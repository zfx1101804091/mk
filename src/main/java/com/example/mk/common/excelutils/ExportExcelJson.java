package com.example.mk.common.excelutils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ExportExcelJson {

    public void exportExcel(String[] headers, JSONArray jsonData, OutputStream out, String title) throws Exception {
    	exportExcel(title, headers, jsonData, out);

    }
    public void exportExcelForMoreSheet(String[] headers, JSONArray jsonData,OutputStream out,String title) throws Exception {
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	for(int i=0;i<jsonData.size();i++){
    		JSONObject job = jsonData.getJSONObject(i);
    		makeSheet(workbook, i, job.getString("name"), headers, JSONArray.fromObject(job.get("arr")));
    	}
    	workbook.write(out);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            json对象属性名称
     * @param jsonData
     *            json格式的数据
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void exportExcel(String title, String[] headers, JSONArray jsonData, OutputStream out) throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        int max = 20000;
        //try {
	        if(jsonData.size()>max){
	        	int num = (jsonData.size()/max)+1;
	        	Object obj = jsonData.get(0);
	        	for(int i=1;i<=num;i++){
	        		if(i==1){
	        			JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
	        			makeSheet(workbook,i-1,"sheet"+(i),headers,jd);
	        		}else if(i<num){
	        			JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
	        			jd.add(0, obj);
	        			makeSheet(workbook,i-1,"sheet"+(i),headers,jd);
	        		}else{
	        			JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, jsonData.size()));
	        			jd.add(0, obj);
	        			makeSheet(workbook,i-1,"sheet"+(i),headers,jd);
	        		}
	        	}
	        }else{
	        	makeSheet(workbook,0,"sheet1",headers,jsonData);
	        }

            workbook.write(out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    private void makeSheet(HSSFWorkbook workbook,int num,String name, String[] headers, JSONArray jsonData){
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(num, name);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
       /* HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");*/

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        for(int i=0;i<jsonData.size();i++){
        	JSONObject jsob=jsonData.getJSONObject(i);
        	HSSFRow row = sheet.createRow(i);
        	boolean flag = false;
        	for(String str:headers){
        		if(str.equals("operator_plate_content")){
        			flag = true;
        		}
        	}
        	for(int j=0;j<headers.length;j++){
        		String tit=headers[j];
        		HSSFCell cell = row.createCell(j);
        		if(i==0){//如果是首行，标题行
        			cell.setCellStyle(style);
                    HSSFRichTextString text;
                    if(jsob.containsKey(tit)){
                        text = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        text = new HSSFRichTextString("");
                    }
                    cell.setCellValue(text);
        		}else{
        			cell.setCellStyle(style2);

                    HSSFRichTextString richString;
                    if(jsob.containsKey(tit)){
                        richString = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        richString = new HSSFRichTextString("");
                    }

        			richString.applyFont(font3);
        			cell.setCellValue(richString);
        		}
        	}
        	if(flag){
        		CellRangeAddress cra=new CellRangeAddress(i, i, 5, 19);
				sheet.addMergedRegion(cra);
				for(int x=6;x<20;x++){
					HSSFCell cell = row.createCell(x);
					if(i==0){//如果是首行，标题行
						cell.setCellStyle(style);
					}else{
						cell.setCellStyle(style2);
					}
				}
    		}
        }

    }


	/**
	 * 重载了exportExcel方法，目的：在excel第一行加入筛选条件
	 */
	public void exportExcel(String[] headers, JSONArray jsonData, OutputStream out, String title, Map<String, String> queryContent) throws Exception {
		exportExcel(title, headers, jsonData, out, queryContent);

	}
	/**
	 * 重载了exportExcel方法，目的：在excel第一行加入筛选条件
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * @param title 表格标题名
	 * @param headers json对象属性名称
	 * @param jsonData json格式的数据
	 * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void exportExcel(String title, String[] headers, JSONArray jsonData, OutputStream out ,Map<String, String> queryContent) throws Exception {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		int max = 20000;
		//try {
		if(jsonData.size()>max){
			int num = (jsonData.size()/max)+1;
			Object obj = jsonData.get(0);
			for(int i=1;i<=num;i++){
				if(i==1){
					JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
					makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent);
				}else if(i<num){
					JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
					jd.add(0, obj);
					makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent);
				}else{
					JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, jsonData.size()));
					jd.add(0, obj);
					makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent);
				}
			}
		}else{
			makeSheet(workbook,0,"sheet1",headers,jsonData,queryContent);
		}
		workbook.write(out);
	}

	/**
	 *  重载了exportExcel方法，目的：在excel第一行加入筛选条件
	 */
    private void makeSheet(HSSFWorkbook workbook,int num,String name, String[] headers, JSONArray jsonData,Map<String, String> queryMap){
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(num, name);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);


        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);


        /*------------------------------设置查询条件----------------------------------------------*/
        // 表头标题样式
        // HSSFFont headfont = workbook.createFont();
        // headfont.setFontName("宋体");
        // headfont.setFontHeightInPoints((short) 10);// 字体大小
        // HSSFCellStyle headstyle = workbook.createCellStyle();
        // headstyle.setFont(headfont);
        // headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        // headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        // headstyle.setLocked(true);
        // headstyle.setWrapText(true); //换行
        // 第一行表头标题
        // sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        HSSFCellStyle queryStyle = workbook.createCellStyle();
        // 设置这些样式
        queryStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        queryStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        queryStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        queryStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font1 = workbook.createFont();
        font1.setColor(HSSFColor.SEA_GREEN.index);
        font1.setFontHeightInPoints((short) 10);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        queryStyle.setFont(font1);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell11 = row1.createCell(0);
        cell11.setCellStyle(style);
        cell11.setCellValue("查询条件：");
        // sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

        int r = 0;
        for (String key : queryMap.keySet()) {
            r++;
            HSSFRow row = sheet.createRow(r);
            //设置样式
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellStyle(style2);
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellStyle(style2);

            //设置内容
            HSSFRichTextString keyString = new HSSFRichTextString(key);
            keyString.applyFont(font3);
            cell0.setCellValue(keyString);
            HSSFRichTextString valueString = new HSSFRichTextString(queryMap.get(key));
            valueString.applyFont(font3);
            cell1.setCellValue(valueString);
        }
        /*------------------------------设置excel查询条件----------------------------------------------*/



        for(int i=0;i<jsonData.size();i++){
            JSONObject jsob=jsonData.getJSONObject(i);
            HSSFRow row = sheet.createRow(i+queryMap.size()+1);
            boolean flag = false;
            for(String str:headers){
                if(str.equals("operator_plate_content")){
                    flag = true;
                }
            }
            for(int j=0;j<headers.length;j++){
                String tit=headers[j];
                HSSFCell cell = row.createCell(j);
                if(i==0){//如果是首行，标题行
                    cell.setCellStyle(style);
                    HSSFRichTextString text;
                    if(jsob.containsKey(tit)){
                        text = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        text = new HSSFRichTextString("");
                    }
                    cell.setCellValue(text);
                }else{
                    cell.setCellStyle(style2);

                    HSSFRichTextString richString;
                    if(jsob.containsKey(tit)){
                        richString = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        richString = new HSSFRichTextString("");
                    }

                    richString.applyFont(font3);
                    cell.setCellValue(richString);
                }
            }
            if(flag){
                CellRangeAddress cra=new CellRangeAddress(i, i, 5, 19);
                sheet.addMergedRegion(cra);
                for(int x=6;x<20;x++){
                    HSSFCell cell = row.createCell(x);
                    if(i==0){//如果是首行，标题行
                        cell.setCellStyle(style);
                    }else{
                        cell.setCellStyle(style2);
                    }
                }
            }
        }

    }
    /**
     * 重载了exportExcel方法，目的：在excel第一行加入筛选条件,黔东南州人民医院定制支付方式汇总报表
     */
    public void exportExcel(String[] headers, JSONArray jsonData, OutputStream out, String title, Map<String, String> queryContent, List<Map<String, Integer>> titleList) throws Exception {
        exportExcel(title, headers, jsonData, out, queryContent,titleList);

    }
    /**
     * 重载了exportExcel方法，目的：在excel第一行加入筛选条件,黔东南州人民医院定制支付方式汇总报表
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     * @param title 表格标题名
     * @param headers json对象属性名称
     * @param jsonData json格式的数据
     * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void exportExcel(String title, String[] headers, JSONArray jsonData, OutputStream out ,Map<String, String> queryContent,List<Map<String, Integer>> titleList) throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        int max = 20000;
        //try {
        if(jsonData.size()>max){
            int num = (jsonData.size()/max)+1;
            Object obj = jsonData.get(0);
            for(int i=1;i<=num;i++){
                if(i==1){
                    JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
                    makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent,titleList);
                }else if(i<num){
                    JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, i*max));
                    jd.add(0, obj);
                    makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent,titleList);
                }else{
                    JSONArray jd = JSONArray.fromObject(jsonData.subList((i-1)*max, jsonData.size()));
                    jd.add(0, obj);
                    makeSheet(workbook,i-1,"sheet"+(i),headers,jd,queryContent,titleList);
                }
            }
        }else{
            makeSheet(workbook,0,"sheet1",headers,jsonData,queryContent,titleList);
        }
        workbook.write(out);
    }

    /**
     *  重载了exportExcel方法，目的：在excel第一行加入筛选条件,黔东南州人民医院定制支付方式汇总报表
     */
    private void makeSheet(HSSFWorkbook workbook,int num,String name, String[] headers, JSONArray jsonData,Map<String, String> queryMap,List<Map<String, Integer>> titleList){
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(num, name);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);


        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);

        /*------------------------------设置查询条件----------------------------------------------*/
        HSSFCellStyle queryStyle = workbook.createCellStyle();
        // 设置这些样式
        queryStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        queryStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        queryStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        queryStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        queryStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font1 = workbook.createFont();
        font1.setColor(HSSFColor.SEA_GREEN.index);
        font1.setFontHeightInPoints((short) 10);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        queryStyle.setFont(font1);


        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 18));
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell11 = row1.getCell(0);
        if(cell11 == null){
            cell11 = row1.createCell(0);
        }
        style.setWrapText(true);
        cell11.setCellStyle(style);
        cell11.setCellValue(new HSSFRichTextString(queryMap.get("表头")+"\r\n"+queryMap.get("开始时间")+"——"+queryMap.get("结束时间")));
        // cell11.setCellValue("查询条件：queryMap");

        int r = 0;
        // for (String key : queryMap.keySet()) {
        //
        //     HSSFRow row = sheet.createRow(r);
        //     //设置样式
        //     HSSFCell cell0 = row.createCell(0);
        //     cell0.setCellStyle(style2);
        //     HSSFCell cell1 = row.createCell(1);
        //     cell1.setCellStyle(style2);
        //
        //     //设置内容
        //     HSSFRichTextString keyString = new HSSFRichTextString(key);
        //     keyString.applyFont(font3);
        //     cell0.setCellValue(keyString);
        //     HSSFRichTextString valueString = new HSSFRichTextString(queryMap.get(key));
        //     valueString.applyFont(font3);
        //     cell1.setCellValue(valueString);
        //     r++;
        // }
        /*------------------------------合并excel头部----------------------------------------------*/
        if(titleList.size()>0){
            sheet.addMergedRegion(new Region((queryMap.size() + 1), (short) 0, (queryMap.size() + 3), (short) (0)));
            sheet.addMergedRegion(new Region((queryMap.size() + 1), (short) 18, (queryMap.size() + 3), (short) (18)));


            for (int i = 0; i < titleList.size(); i++) {
                HSSFRow row = sheet.createRow(queryMap.size()+1+i);
                Map<String, Integer> titleMap = titleList.get(i);
                //解决合并之后边框消失问题
                for (int j = 0; j < headers.length; j++) {
                    row.createCell(j).setCellStyle(style);
                }
                int cellNum = 0 ;
                for (String key : titleMap.keySet()) {
                    row.getCell(cellNum);
                    if(titleMap.get(key)>1){
                        //合并1, first row ,first column ,last row,last column
                        sheet.addMergedRegion(new Region((queryMap.size() + 1 + i), (short) cellNum, (queryMap.size() + 1 + i), (short) (cellNum + titleMap.get(key) - 1)));
                    }
                    //设置样式
                    HSSFCell cell = row.getCell(cellNum);
                    if(cell == null){
                        cell = row.createCell(cellNum);
                    }
                    cell.setCellStyle(style);

                    //设置内容
                    HSSFRichTextString keyString = new HSSFRichTextString(key.trim());
                    keyString.applyFont(font);
                    cell.setCellValue(keyString);

                    cellNum += titleMap.get(key);
                }
            }
        }

        for(int i=0;i<jsonData.size();i++){
            JSONObject jsob=jsonData.getJSONObject(i);
            HSSFRow row = sheet.createRow(i+queryMap.size()+titleList.size()+1);
            boolean flag = false;
            for(String str:headers){
                if(str.equals("operator_plate_content")){
                    flag = true;
                }
            }
            for(int j=0;j<headers.length;j++){
                String tit=headers[j];
                HSSFCell cell = row.createCell(j);
                if(i==0){//如果是首行，标题行
                    cell.setCellStyle(style);
                    HSSFRichTextString text;
                    if(jsob.containsKey(tit)){
                        text = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        text = new HSSFRichTextString("");
                    }
                    cell.setCellValue(text);
                }else{
                    cell.setCellStyle(style2);

                    HSSFRichTextString richString;
                    if(jsob.containsKey(tit)){
                        richString = new HSSFRichTextString(jsob.getString(tit));
                    }else{
                        richString = new HSSFRichTextString("");
                    }

                    richString.applyFont(font3);
                    cell.setCellValue(richString);
                }
            }
            if(flag){
                CellRangeAddress cra=new CellRangeAddress(i, i, 5, 19);
                sheet.addMergedRegion(cra);
                for(int x=6;x<20;x++){
                    HSSFCell cell = row.createCell(x);
                    if(i==0){//如果是首行，标题行
                        cell.setCellStyle(style);
                    }else{
                        cell.setCellStyle(style2);
                    }
                }
            }
        }
        HSSFRow row_last = sheet.createRow(7+jsonData.size());
        HSSFCell cell_last_0 = row_last.getCell(0);
        if(cell_last_0 == null){
            cell_last_0 = row_last.createCell(0);
        }
        HSSFRichTextString keyString = new HSSFRichTextString("操作员：");
        keyString.applyFont(font);
        cell_last_0.setCellValue(keyString);

        HSSFCell cell_last_3 = row_last.getCell(2);
        if(cell_last_3 == null){
            cell_last_3 = row_last.createCell(2);
        }
        HSSFRichTextString keyString3 = new HSSFRichTextString("审核：");
        keyString3.applyFont(font);
        cell_last_3.setCellValue(keyString3);
    }

}
