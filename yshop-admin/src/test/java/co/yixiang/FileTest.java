/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang;

import cn.hutool.core.img.ImgUtil;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ：LionCity
 * @date ：Created in 2020-03-24 16:45
 * @description：
 * @modified By：
 * @version:
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        readfile("D:/upload");
    }

    public static void readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());

                File targetFile = new File(file.getPath().replace("upload", "uploadZip"));
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                ImgUtil.scale(file, targetFile, getAccuracy(file.length() / 1024));
            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());
                        File targetFile = new File(readfile.getPath().replace("upload", "uploadZip"));
                        System.out.println("path2=" + targetFile.getPath());
                        System.out.println("fileSize=" + targetFile.length());
                        if (!targetFile.getParentFile().exists()) {
                            targetFile.getParentFile().mkdirs();
                        }
                        ImgUtil.scale(readfile, targetFile, getAccuracy(file.length() / 1024));
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
    }

    public static BufferedImage inputImage(MultipartFile file) {
        BufferedImage srcImage = null;
        try {
            FileInputStream in = (FileInputStream) file.getInputStream();
            srcImage = javax.imageio.ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("读取图片文件出错！" + e.getMessage());
        }
        return srcImage;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    public static float getAccuracy(long size) {
        float accuracy;
        if (size < 400) {
            accuracy = 0.85f;
        } else if (size < 900) {
            accuracy = 0.75f;
        } else if (size < 2047) {
            accuracy = 0.6f;
        } else if (size < 3275) {
            accuracy = 0.44f;
        } else {
            accuracy = 0.4f;
        }
        return accuracy;
    }
}
