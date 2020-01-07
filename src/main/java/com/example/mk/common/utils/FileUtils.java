package com.example.mk.common.utils;

import java.io.File;

public class FileUtils {
    /**
     * 方法描述 :删除文件
     * 如果文件是目录，将目录下的所有文件一起删除
     */
    public static void deleteFileAndDir(String workspaceRootPath){
        File file = new File(workspaceRootPath);
        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(int i=0; i<files.length; i++){
                    files[i].delete();
                }
            }
            file.delete();
        }
    }

    /**
     * 方法描述 :删除给定的文件
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
}
