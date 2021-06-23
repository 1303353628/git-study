package zhan.wang.moon.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import zhan.wang.moon.log.MoonSeqLogger;

/**
 * 
 * JSON 处理类
 */
public class JsonUtil {
    
    private JsonUtil() {
        // private constructor
    }

    /**
     * 把对象变成JSON字符串
     * @param object 对象
     * @return JSON字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
    
    /**
     * 把JSON字符串变成JSON对象
     * @param text 字符串
     * @return {@link com.alibaba.fastjson.JSONObject}
     */
    public static JSONObject parseObject(String text) {
        return JSON.parseObject(text);
    }

    /**
     * 把JSON字符串变成对象
     * @param <T> 转换类型
     * @param text JSON字符串
     * @param clazz 对象类定义
     * @return 转换后对象
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    
    /**
     * 把JSON字符串变成JSON数组
     * @param text 字符串
     * @return {@link com.alibaba.fastjson.JSONArray}
     */
    public static JSONArray parseArray(String text) {
        return JSON.parseArray(text);
    }
    
    /**
     * 从JSON字符串中获得属性值
     * @param json JSON字符串
     * @param key 属性键值
     * @return 属性值
     */
    public static String getValueFromJson(String json, String key) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            return jsonObject.getString(key);
        } catch (Exception e) {
            MoonSeqLogger.error("Parse JSON {} with exception: ", json, e);
            return null;
        }
    }
    
}
