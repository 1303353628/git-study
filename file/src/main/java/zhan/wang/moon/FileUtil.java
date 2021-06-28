package zhan.wang.moon;


import zhan.wang.moon.encode.MD5Util;
import zhan.wang.moon.log.MoonSeqLogger;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {

    private MessageDigest mMessageDigest = null;

    /**
     * @param sourcePath
     * @return
     * @auther zhiy.wang
     * @description 根据源路径返回该路径下所有文件路径
     */
    public List<File> readFiles(File sourcePath) {
        List<File> returnFiles = new ArrayList<File>();
        if (sourcePath.isFile()) {
            returnFiles.add(sourcePath);
        } else if (sourcePath.isDirectory()) {
            try {
                for (File file : sourcePath.listFiles()) {
                    if (file.isFile()) {
                        returnFiles.add(file);
                    } else {
                        returnFiles.addAll(readFiles(file));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnFiles;
    }

    public void deleteFileStr(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public void delDictionary(String sourcePath) {
        File sourceFile = new File(sourcePath);
        if (sourceFile.isDirectory()) {
            if (sourceFile.listFiles().length == 0) {
                System.out.println(sourceFile.getAbsoluteFile());
                sourceFile.delete();
            }else {
                delDictionary(sourceFile);
            }
        }
    }


    public void delDictionary(File sourceFile) {
        if (sourceFile.isDirectory()) {
            if (sourceFile.listFiles().length == 0) {
                System.out.println(sourceFile.getAbsoluteFile());
                sourceFile.delete();
            } else {
                for (File file:sourceFile.listFiles()) {
                    delDictionary(file);
                }
            }
        }
    }


    /**
     * 对比两个文件是否相同 boolean类型，相同为true
     *
     * @param oldFilePath
     * @param newFilePath
     * @return
     */
    public boolean compareFile(File oldFilePath, File newFilePath) {

        //跳过不同的文件格式
        if (!oldFilePath.getName().substring(oldFilePath.getName().lastIndexOf(".") + 1).equals(oldFilePath.getName().substring(oldFilePath.getName().lastIndexOf(".") + 1))) {
            MoonSeqLogger.info("文件格式不同，跳过\t 旧文件为：{}，新文件为：{}", oldFilePath.getAbsolutePath(), newFilePath.getAbsolutePath());
            return false;
        }

        if (oldFilePath.getName().length() != newFilePath.getName().length()) {
            String oldFileMD5 = null;
            String newFileMD5 = null;
            oldFileMD5 = MD5Util.getFileMD5StringBase16(oldFilePath);
            newFileMD5 = MD5Util.getFileMD5StringBase16(newFilePath);

            if (newFileMD5 == null || oldFileMD5 == null || !oldFileMD5.equals(newFileMD5)) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 文件转移
     * @param fileName 文件名
     * @param destPath
     */
    public void moveFile(String fileName,String destPath) {
        File file = new File(fileName);
        File newFile = new File(destPath + File.separator + fileName);
        if (file.isFile()) {
            file.renameTo(newFile);
        }else {
            File[] files = file.listFiles();
            for (File tempFile:files) {
                moveFile(tempFile.getName(), destPath);
            }
        }
    }

}
