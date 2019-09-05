package com.dawang;

import com.dawang.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestDemo {
    private static Logger logger = LoggerFactory.getLogger(TestDemo.class);

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        String oldPath = "E:\\百度云下载\\test123\\save";
        String newPath = "E:\\百度云下载\\test123\\temp";
//        oldPath = "E:\\百度云下载\\test123\\temp";
//        newPath = "E:\\百度云下载\\test123\\temp2";
        List<File> oldFiles = new ArrayList<File>();
        List<File> newFiles = new ArrayList<File>();
        oldFiles = fileUtil.readFiles(new File(oldPath));
        newFiles = fileUtil.readFiles(new File(newPath));
        Collections.sort(oldFiles);
        Collections.sort(newFiles);

        HashMap<String, File> oldMap = new HashMap<String, File>();
        HashMap<String, File> newMap = new HashMap<String, File>();
        String tempMD5Str = null;
        logger.info("===============begin=====================");
        for (File newFile : newFiles) {
            logger.info("剩余待读取文件个数{}",newFiles.size()-newMap.size());
            tempMD5Str = fileUtil.getFileMD5String(newFile);
            if (newMap.containsKey(tempMD5Str)) {
                logger.info("重复文件，删除：{}", newFile.getAbsolutePath());
                newFile.delete();

            } else {
                logger.info("放入newMap的文件为{}", newFile.getAbsolutePath());
                newMap.put(tempMD5Str, newFile);
            }
        }
        for (File oldFile : oldFiles) {
            logger.info("剩余待读取文件个数：{}" ,oldFiles.size() - oldMap.size());
            tempMD5Str = fileUtil.getFileMD5String(oldFile);
            if (oldMap.containsKey(tempMD5Str)) {
                logger.info("重复文件，删除：{}", oldFile.getAbsolutePath());
                oldFile.delete();
            } else {
                logger.info("oldMpa中放入：{}", oldFile.getAbsolutePath());
                oldMap.put(tempMD5Str, oldFile);
            }
        }


        //开始对比Map中元素
        if (!newMap.isEmpty()) {
            for (String key : newMap.keySet()) {
                logger.info("开始对比文件key:{},value{}", key, newMap.get(key));
                if (oldMap.containsKey(key)) {
                    logger.info("删除文件key:{},value{}", key, newMap.get(key));
                    newMap.get(key).delete();
                }
                logger.info("结束对比文件key:{},value{}", key, newMap.get(key));
            }
        }
        logger.info("============================end=========================");
    }

}
