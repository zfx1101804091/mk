package com.example.mk.common.excelutils;

import com.example.mk.common.utils.CommonUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用开源组件POI动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 * 
 * @author leno
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {

	public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("测试POI导出EXCEL文档", null, dataset, out, DateFormatUtil.dateHasLineDay);
    }

    public void exportExcel(String[] headers, Collection<T> dataset,OutputStream out) {
        exportExcel("导出EXCEL", headers, dataset, out, DateFormatUtil.dateHasLineDay);
    }

    public void exportExcel(String[] headers, Collection<T> dataset,OutputStream out, String pattern) {
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
    }
    public void exportExcel(String[] headers, Collection<T> dataset,OutputStream out, String pattern,String title) {
    	exportExcel(title, headers, dataset, out, pattern);
    }
    public void exportExcelBadDebt(String[] headers, Collection<T> dataset,OutputStream out, String pattern,String title) {
    	exportExcelBadDebt(title, headers, dataset, out, pattern);
    }
    public void exportExcel(String[] headers, Collection<T> dataset,String filePath, String pattern,String title) {
    	//exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
    	generateExcel(title, headers, dataset, pattern, filePath);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings("unchecked")
    public void exportExcel(String title, String[] headers,Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
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
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");
                                                                                  
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            //System.out.println(fields);
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Field field = fields[i];
                
                String fieldName = field.getName();
                //System.out.println("-------fieldName="+fieldName+"----------0,1="+fieldName.substring(0, 1)+"------------1="+fieldName.substring(1));
                String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
               // System.out.println("-------getMethodName="+getMethodName);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // if (value instanceof Integer) {
                    // int intValue = (Integer) value;
                    // cell.setCellValue(intValue);
                    // } else if (value instanceof Float) {
                    // float fValue = (Float) value;
                    // textValue = new HSSFRichTextString(
                    // String.valueOf(fValue));
                    // cell.setCellValue(textValue);
                    // } else if (value instanceof Double) {
                    // double dValue = (Double) value;
                    // textValue = new HSSFRichTextString(
                    // String.valueOf(dValue));
                    // cell.setCellValue(textValue);
                    // } else if (value instanceof Long) {
                    // long longValue = (Long) value;
                    // cell.setCellValue(longValue);
                    // }
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "男";
                        if (!bValue) {
                            textValue = "女";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                       // SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = CommonUtils.format(date, pattern);//sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        //textValue = value.toString();
                    	textValue = value==null?"":value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLUE.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void exportExcelBadDebt(String title, String[] headers,Collection<T> dataset, OutputStream out, String pattern) {
    	// 声明一个工作薄
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	// 生成一个表格
    	HSSFSheet sheet = workbook.createSheet(title);
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
    	
    	// 生成并设置另一个样式
    	HSSFCellStyle style2_r = workbook.createCellStyle();
    	style2_r.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    	style2_r.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	style2_r.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    	style2_r.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    	style2_r.setBorderRight(HSSFCellStyle.BORDER_THIN);
    	style2_r.setBorderTop(HSSFCellStyle.BORDER_THIN);
    	style2_r.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    	style2_r.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    	
    	style2_r.setFont(font2);
    	
    	
    	
    	
    	
    	// 生成并设置另一个样式
    	/*HSSFCellStyle style_right = workbook.createCellStyle();
    	style_right.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    	style_right.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	style_right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    	style_right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    	style_right.setBorderRight(HSSFCellStyle.BORDER_THIN);
    	style_right.setBorderTop(HSSFCellStyle.BORDER_THIN);
    	style_right.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    	style_right.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    	// 生成另一个字体
    	HSSFFont fontx = workbook.createFont();
    	fontx.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    	fontx.setColor(HSSFFont.COLOR_RED);
    	
    	style_right.setFont(fontx);*/
    	
    	
    	
    	// 声明一个画图的顶级管理器
    	HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    	// 定义注释的大小和位置,详见文档
    	HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
    	// 设置注释内容
    	comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    	// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
    	comment.setAuthor("leno");
    	
    	// 产生表格标题行
    	HSSFRow row = sheet.createRow(0);
    	for (int i = 0; i < headers.length; i++) {
    		HSSFCell cell = row.createCell(i);
    		cell.setCellStyle(style);
    		//cell.setCellStyle(style_right);
    		HSSFRichTextString text = new HSSFRichTextString(headers[i]);
    		cell.setCellValue(text);
    	}
    	
    	// 遍历集合数据，产生数据行
    	Iterator<T> it = dataset.iterator();
    	int index = 0;
    	while (it.hasNext()) {
    		index++;
    		row = sheet.createRow(index);
    		T t = (T) it.next();
    		// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
    		Field[] fields = t.getClass().getDeclaredFields();
    		//System.out.println(fields);
    		for (int i = 0; i < fields.length; i++) {
    			HSSFCell cell = row.createCell(i);
    			//cell.setCellStyle(style_right);
    			cell.setCellStyle(style2);
    			if(i>=5&&i!=10){
    				cell.setCellStyle(style2_r);
    			}
    			//cell.setCellStyle(style_right);
    			Field field = fields[i];
    			
    			String fieldName = field.getName();
    			//System.out.println("-------fieldName="+fieldName+"----------0,1="+fieldName.substring(0, 1)+"------------1="+fieldName.substring(1));
    			String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
    			// System.out.println("-------getMethodName="+getMethodName);
    			try {
    				Class tCls = t.getClass();
    				Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
    				Object value = getMethod.invoke(t, new Object[] {});
    				// 判断值的类型后进行强制类型转换
    				String textValue = null;
    				// if (value instanceof Integer) {
    				// int intValue = (Integer) value;
    				// cell.setCellValue(intValue);
    				// } else if (value instanceof Float) {
    				// float fValue = (Float) value;
    				// textValue = new HSSFRichTextString(
    				// String.valueOf(fValue));
    				// cell.setCellValue(textValue);
    				// } else if (value instanceof Double) {
    				// double dValue = (Double) value;
    				// textValue = new HSSFRichTextString(
    				// String.valueOf(dValue));
    				// cell.setCellValue(textValue);
    				// } else if (value instanceof Long) {
    				// long longValue = (Long) value;
    				// cell.setCellValue(longValue);
    				// }
    				if (value instanceof Boolean) {
    					boolean bValue = (Boolean) value;
    					textValue = "男";
    					if (!bValue) {
    						textValue = "女";
    					}
    				} else if (value instanceof Date) {
    					Date date = (Date) value;
    					//SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    					textValue = CommonUtils.format(date, pattern);//sdf.format(date);
    				} else if (value instanceof byte[]) {
    					// 有图片时，设置行高为60px;
    					row.setHeightInPoints(60);
    					// 设置图片所在列宽度为80px,注意这里单位的一个换算
    					sheet.setColumnWidth(i, (short) (35.7 * 80));
    					// sheet.autoSizeColumn(i);
    					byte[] bsValue = (byte[]) value;
    					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
    							1023, 255, (short) 6, index, (short) 6, index);
    					anchor.setAnchorType(2);
    					patriarch.createPicture(anchor, workbook.addPicture(
    							bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
    				} else {
    					// 其它数据类型都当作字符串简单处理
    					//textValue = value.toString();
    					textValue = value==null?"":value.toString();
    				}
    				
    				
    				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
    				if (textValue != null) {
    					Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
    					Matcher matcher = p.matcher(textValue);
    					if (matcher.matches()) {
    						// 是数字当作double处理
    						cell.setCellValue(Double.parseDouble(textValue));
    					} else {
    						HSSFRichTextString richString = new HSSFRichTextString(
    								textValue);
    						HSSFFont font3 = workbook.createFont();
    						font3.setColor(HSSFColor.BLUE.index);
    						richString.applyFont(font3);
    						cell.setCellValue(richString);
    					}
    				}
    			} catch (SecurityException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (NoSuchMethodException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IllegalArgumentException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IllegalAccessException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (InvocationTargetException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} finally {
    				// 清理资源
    			}
    		}
    		
    	}
    	try {
    		workbook.write(out);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }
    
    
    public void generateExcel(String title, String[] headers,Collection<T> dataset, String pattern,String filePath){
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        //sheet.setDefaultColumnWidth((short) 15);
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
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            //System.out.println(fields);
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Field field = fields[i];
                
                String fieldName = field.getName();
                //System.out.println("-------fieldName="+fieldName+"----------0,1="+fieldName.substring(0, 1)+"------------1="+fieldName.substring(1));
                String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
               // System.out.println("-------getMethodName="+getMethodName);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // if (value instanceof Integer) {
                    // int intValue = (Integer) value;
                    // cell.setCellValue(intValue);
                    // } else if (value instanceof Float) {
                    // float fValue = (Float) value;
                    // textValue = new HSSFRichTextString(
                    // String.valueOf(fValue));
                    // cell.setCellValue(textValue);
                    // } else if (value instanceof Double) {
                    // double dValue = (Double) value;
                    // textValue = new HSSFRichTextString(
                    // String.valueOf(dValue));
                    // cell.setCellValue(textValue);
                    // } else if (value instanceof Long) {
                    // long longValue = (Long) value;
                    // cell.setCellValue(longValue);
                    // }
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "男";
                        if (!bValue) {
                            textValue = "女";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                       // SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = CommonUtils.format(date, pattern);//sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value==null?"":value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLUE.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
        try {
    		//定义文件路径

        	FileOutputStream out = new FileOutputStream(filePath);
        	//workbook.write(fOut);
            workbook.write(out);
            out.flush();
           // 操作结束，关闭文件　　
            out.close(); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    

/*    public static void main(String[] args) {
        // 测试学生
        ExportExcel1<Student> ex = new ExportExcel1<Student>();
        String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
        List<Student> dataset = new ArrayList<Student>();
        dataset.add(new Student(10000001, "张三", 20, true, new Date()));
        dataset.add(new Student(20000002, "李四", 24, false, new Date()));
        dataset.add(new Student(30000003, "王五", 22, true, new Date()));
        // 测试图书
        ExportExcel1<Book> ex2 = new ExportExcel1<Book>();
        String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
                "图书出版社", "封面图片" };
        List<Book> dataset2 = new ArrayList<Book>();
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream("book.jpg"));
            byte[] buf = new byte[bis.available()];
            while ((bis.read(buf)) != -1) {
                //
            }
            dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
                    "阳光出版社", buf));
            dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
                    "汤春秀出版社", buf));

            OutputStream out = new FileOutputStream("E://a.xls");
            OutputStream out2 = new FileOutputStream("E://b.xls");
            ex.exportExcel(headers, dataset, out);
            ex2.exportExcel(headers2, dataset2, out2);
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    
}
