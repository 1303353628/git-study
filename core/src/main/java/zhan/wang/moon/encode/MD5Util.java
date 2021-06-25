package zhan.wang.moon.encode;

import zhan.wang.moon.exception.EarthBaseException;
import zhan.wang.moon.exception.StatusCode;
import zhan.wang.moon.log.MoonSeqLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new EarthBaseException(StatusCode.ENCODE_ERR.getCode(), "获取MD5摘要算法失败", e);
        }
    }

    /**
     * 获得MD5值 BASE16编码
     * @param source 输入值
     * @return MD5字符串
     */
    public static String getStringMD5Base16(String source) {
        messageDigest.update(source.getBytes());
        byte[] digest = messageDigest.digest();
        return HexUtil.byte2hex(digest);
    }

    public static String getFileMD5StringBase16(File file) {
        String FileMD5String = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                messageDigest.update(buffer, 0, length);
            }
            fis.close();
            FileMD5String = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MoonSeqLogger.info("文件名：{}，MD5：{}，大小为：{}M", file.getAbsolutePath(), FileMD5String, file.length() / 1024 / 1024);
//        System.out.println("文件名：" + file.getAbsolutePath() + "\tMD5" + MD5Str + "\t大小为" + file.length()/1024/1024 + "M");
        return FileMD5String;
    }

}
