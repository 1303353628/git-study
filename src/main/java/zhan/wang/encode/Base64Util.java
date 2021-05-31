package zhan.wang.encode;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import zhan.wang.exception.EarthBaseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class Base64Util {

    private final static Base64.Decoder DECODER = Base64.getDecoder();
    private final static Base64.Encoder ENCODER = Base64.getEncoder();

    private Base64Util() {
        // private constructor
    }

    /**
     * 把Base64编码的字符串反编码成二进制数据
     * @param input 输入字符串
     * @return  二进制数据
     */
    public static byte[] decode(String input) {
        if (StringUtils.isEmpty(input)) {
            return new byte[0];
        }
        return DECODER.decode(input);
    }

    /**
     * 把Base64编码的字符串反编码成原字符串
     * @param input 输入字符串
     * @return  原字符串
     */
    public static String decodeToString(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }
        return new String(decode(input));
    }

    /**
     * 把二进制数据变成Base64编码的字符串
     * @param data 二进制数据
     * @return  编码后字符串
     */
    public static String encodeToString(byte[] data) {
        return ENCODER.encodeToString(data);
    }

    /**
     * 把字符串变成Base64编码的字符串
     * @param input 字符串（UTF-8编码）
     * @return 编码后字符串
     */
    public static String encodeToString(String input) {
        return ENCODER.encodeToString(input.getBytes());
    }

    /**
     * 获得一个文件的Base64编码
     * @param file  文件对象
     * @return  Base64编码字符串
     */
    public static String convertBase64(File file) {
        if (file == null) {
            throw new EarthBaseException(new NullPointerException());
        }
        if (!file.exists()) {
            throw new EarthBaseException(file.getAbsoluteFile().toString(), new FileNotFoundException());
        }
        try {
            StringBuilder result = new StringBuilder();
            byte[] data = FileUtils.readFileToByteArray(file);
            return result.append(ENCODER.encodeToString(data)).toString();
        } catch (IOException e) {
            throw new EarthBaseException(e);
        }
    }

    /**
     * 获得一个文件的Base64编码
     * @param path  文件路径
     * @return  Base64编码字符串
     */
    public static String convertBase64(String path) {
        if (StringUtils.isEmpty(path)) {
            throw new EarthBaseException("文件路径为空");
        }
        return convertBase64(new File(path));
    }

    /**
     * Base64编码转文件
     * @param code  Base64编码字符串
     * @param path  目标文件路径
     * @return  {@link java.io.File}
     * @throws IOException  文件处理异常
     */
    public static File base64ToFile(String code, String path) throws IOException {
        File file = new File(path);
        byte[] data = DECODER.decode(code);
        FileUtils.writeByteArrayToFile(file, data);
        return file;
    }
}
