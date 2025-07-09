package com.dzhStudy.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ZiHao Deng
 * @Created: 2023/4/8 12:59
 */
public class FileUploadUtil {
    public static String BasePath = "";
    private static String LINUXPATH = "/classmate/upload/";//如果是linux操作系统，则缓存地址为
    private static String MACPATH = "/Users/classmate/upload/";//如果是Mac操作系统，则缓存地址为
    private static String WINDOWSPATH = "D:/javacode/hellospringboot/demo/upload/";//如果是Windows操作系统，则缓存地址为
    //根目录
    private static String ROOT = "upload/";

    static {
        initBasePath();
    }

    /**
     * 系统初始化上传根目录
     */
    public static void initBasePath() {
        BasePath = getBasePath();
        File BasePathFile = new File(BasePath);
        if (!BasePathFile.exists()) {
            BasePathFile.mkdirs();
        }
    }

    /**
     *   获取当前环境的路径
     */
    public static String getBasePath() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return LINUXPATH;
        } else if (os != null && os.toLowerCase().indexOf("mac") > -1) {
            return MACPATH;
        } else {
            return WINDOWSPATH;
        }
    }

    /**
     *
     * @param file 流
     * @param folderName 文件夹名称, 如: C:/classmate/upload/ (folderName文件名)
     * @return
     */
    public static String uploadFile(MultipartFile file,String folderName){
        FileOutputStream fot = null;
        //获取上传到的路径地址
        String path = getBasePath();
        path = path + folderName;

        //文件名称
        String filePath = getFilePath(file,path);
        File dest = new File(path + "/" + filePath);
        try {
            dest.createNewFile();//创建一个文件
            fot = new FileOutputStream(dest);
            byte[] bytes = file.getBytes();
            fot.write(bytes);

            //根目录: upload/ + 自定义目录: user/  + 文件名: xxxx.jpg
            return ROOT + folderName + "/" +filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(fot != null){
                try {
                    fot.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param path 文件夹名字
     * @param file     file文件
     * @return
     */
    private static String getFilePath(MultipartFile file, String path) {
        //生成文件名称(防止重复)
        long date = System.currentTimeMillis();
        //检测是否创建
        if (!new File(path).isDirectory()) {
            new File(path).mkdirs();
        }
        //截取文件后缀 如:xxx.jpg xxx.png
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        //拼接新的文件名称
        String name = date + suffix;
        //生成路径加上名字
        return  name;
    }

}


