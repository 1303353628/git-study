package zhan.wang.moon.encode;

/**
 *
 * 16进制编码转换类
 */
public class HexUtil {

    private HexUtil() {
        // private constructor
    }

    /**
     * 把字节流转换成十六进制字符（默认大写）
     * @param bytes 字节流数组
     * @return 十六进制字符串
     */
    public static String byte2hex(byte[] bytes) {
        return byte2hex(bytes, false);
    }

    /**
     * 把字节流转换成十六进制字符串
     * @param bytes 字节流数组
     * @param toLowerCase 是否小写
     * @return 十六进制字符串
     */
    public static String byte2hex(byte[] bytes, boolean toLowerCase) {
        StringBuilder builder = new StringBuilder();
        String hex;
        for (int i = 0; i < bytes.length; i++) {
            hex = Integer.toHexString(bytes[i] & 0xFF);
            if (toLowerCase) {
                hex = hex.toLowerCase();
            } else {
                hex = hex.toUpperCase();
            }
            if (hex.length() == 1) {
                builder.append("0").append(hex);
            } else {
                builder.append(hex);
            }
        }
        return builder.toString();
    }

    /**
     * 把十六进制字符串转换成字节流
     * @param hex  十六进制字符串
     * @return 字节流数组
     */
    public static byte[] hex2byte(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("invalid hex string");
        }
        char[] array = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        for (int aIndex = 0, bIndex = 0, len = hex.length(); aIndex < len; aIndex+=2, bIndex++) {
            String swap = "" + array[aIndex] + array[aIndex+1];
            Integer byteInt = Integer.parseInt(swap, 16) & 0xFF;
            bytes[bIndex] = byteInt.byteValue();
        }
        return bytes;
    }
}
