package zhan.wang.moon;


import zhan.wang.moon.log.MoonSeqLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                        if (!file.getName().contains("downloading")) {
                            returnFiles.add(file);
                        }
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

    public String getFileMD5String(File file) {
        String MD5Str = null;
        try {
            mMessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                mMessageDigest.update(buffer, 0, length);
            }
            fis.close();
            MD5Str = new BigInteger(1, mMessageDigest.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MoonSeqLogger.info("文件名：{}，MD5：{}，大小为：{}M", file.getAbsolutePath(), MD5Str, file.length() / 1024 / 1024);
//        System.out.println("文件名：" + file.getAbsolutePath() + "\tMD5" + MD5Str + "\t大小为" + file.length()/1024/1024 + "M");
        return MD5Str;
    }

    public void deleteFileStr(String filePath) {
        File file = new File(filePath);
        file.delete();
    }


    /**
     * 对比两个文件是否相同 boolean类型，相同为true
     *
     * @param oldFilePath
     * @param newFilePath
     * @return
     */
    public boolean compareFile(File oldFilePath, File newFilePath) {

//        File oldFile = new File(oldFilePath);
//        File newFile = new File(newFilePath);
//        System.out.println(oldFile.getName());
//        file.getName().substring(file.getName().lastIndexOf(".")+1)
//        System.out.println(oldFile.getAbsoluteFile());
//        System.out.println(oldFile.getAbsoluteFile().toString().substring(oldFile.getAbsoluteFile().toString().lastIndexOf(".")+1));
        //跳过不同的文件格式
        if (!oldFilePath.getName().substring(oldFilePath.getName().lastIndexOf(".") + 1).equals(oldFilePath.getName().substring(oldFilePath.getName().lastIndexOf(".") + 1))) {
//            System.out.println("文件格式不同，跳过\t" + oldFilePath.getAbsolutePath() + "\t" + oldFilePath.getAbsolutePath());
            MoonSeqLogger.info("文件格式不同，跳过\t 旧文件为：{}，新文件为：{}", oldFilePath.getAbsolutePath(), newFilePath.getAbsolutePath());
            return false;
        }

        if (oldFilePath.getName().length() != newFilePath.getName().length()) {
            String oldFileMD5 = null;
            String newFileMD5 = null;
            oldFileMD5 = getFileMD5String(oldFilePath);
            newFileMD5 = getFileMD5String(newFilePath);

            if (newFileMD5 == null || oldFileMD5 == null || !oldFileMD5.equals(newFileMD5)) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    public void moveFile(File file,String destPath) {
        File newFile = new File(destPath + File.separator + file.getName());
        if (file.isFile()) {
            file.renameTo(newFile);
        }else {
            File[] files = file.listFiles();
            for (File tempFile:files  ) {
                moveFile(tempFile, destPath);
            }
        }
    }

}
