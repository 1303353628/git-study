package zhan.wang.encode;

import zhan.wang.exception.EarthBaseException;
import zhan.wang.exception.StatusCode;

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
     * 获得MD5值
     * @param source 输入值
     * @return MD5字符串
     */
    public static String getMD5(String source) {
        messageDigest.update(source.getBytes());
        byte[] digest = messageDigest.digest();
        return HexUtil.byte2hex(digest);
    }

}
