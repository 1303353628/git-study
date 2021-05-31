package zhan.wang.moon.log;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;
import zhan.wang.moon.config.EnvConfig;
import zhan.wang.moon.exception.EarthBaseException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 包含机器名/类方法/外部主键信息的日志类
 * 参考 http://www.slf4j.org/apidocs/org/slf4j/Logger.html
 */
@Log4j2
public class MachSeqLogger {
    
    private static final String CLASS_NAME = MachSeqLogger.class.getName();

    private MachSeqLogger() {
        // private constructor
    }
    
    /***********************************************
     * ERROR 级别方法
     ***********************************************/
    
    /**
     * 带异常ERROR级别信息
     * @param seqMap 外部主键集合
     * @param msg 信息
     * @param t 异常对象
     */
    public static void error(LinkedHashMap<String, String> seqMap, String msg, Throwable t) {
        log.error(buildMessage(seqMap, msg), t);
    }
    
    /**
     * 带异常ERROR级别信息
     * @param msg 信息
     * @param t 异常对象
     */
    public static void error(String msg, Throwable t) {
        error(null, msg, t);
    }
    
    /**
     * ERROR级别信息
     * @param seqMap 外部主键集合
     * @param msg 信息
     */
    public static void error(LinkedHashMap<String, String> seqMap, String msg) {
        log.error(buildMessage(seqMap, msg));
    }
    
    /**
     * ERROR级别信息
     * @param msg 信息
     */
    public static void error(String msg) {
        error(null, msg);
    }
    
    /**
     * 带参数ERROR级别信息
     * @param seqMap 外部主键集合
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void error(LinkedHashMap<String, String> seqMap, String format, Object... arguments) {
        log.error(buildMessage(seqMap, format), arguments);
    }
    
    /**
     * 带参数ERROR级别信息
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void error(String format, Object... arguments) {
        error(null, format, arguments);
    }
    
    /***********************************************
     * WARN 级别方法
     ***********************************************/
    
    /**
     * 带异常WARN级别信息
     * @param seqMap 外部主键集合
     * @param msg 信息
     * @param t 异常对象
     */
    public static void warn(LinkedHashMap<String, String> seqMap, String msg, Throwable t) {
        log.warn(buildMessage(seqMap, msg), t);
    }
    
    /**
     * 带异常WARN级别信息
     * @param msg 信息
     * @param t 异常对象
     */
    public static void warn(String msg, Throwable t) {
        warn(null, msg, t);
    }
    
    /**
     * WARN级别信息
     * @param seqMap 外部主键集合
     * @param msg 信息
     */
    public static void warn(LinkedHashMap<String, String> seqMap, String msg) {
        log.warn(buildMessage(seqMap, msg));
    }
    
    /**
     * WARN级别信息
     * @param msg 信息
     */
    public static void warn(String msg) {
        warn(null, msg);
    }
    
    /**
     * 带参数WARN级别信息
     * @param seqMap 外部主键集合
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void warn(LinkedHashMap<String, String> seqMap, String format, Object... arguments) {
        log.warn(buildMessage(seqMap, format), arguments);
    }
    
    /**
     * 带参数WARN级别信息
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void warn(String format, Object... arguments) {
        warn(null, format, arguments);
    }
    
    /***********************************************
     * INFO 级别方法
     ***********************************************/
    
    /**
     * INFO级别信息
     * @param seqMap 外部主键集合
     * @param msg 信息
     */
    public static void info(LinkedHashMap<String, String> seqMap, String msg) {
        log.info(buildMessage(seqMap, msg));
    }

    /**
     * INFO级别信息
     * @param msg 信息
     */
    public static void info(String msg) {
        info(null, msg);
    }
    
    /**
     * 带参数INFO级别信息
     * @param seqMap 外部主键集合
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void info(LinkedHashMap<String, String> seqMap, String format, Object... arguments) {
        log.info(buildMessage(seqMap, format), arguments);
    }

    /**
     * 带参数INFO级别信息
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void info(String format, Object... arguments) {
        info(null, format, arguments);
    }
    
    /***********************************************
     * DEBUG 级别方法
     ***********************************************/
    
    /**
     * 带参数DEBUG级别信息
     * @param seqMap 外部主键集合
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void debug(LinkedHashMap<String, String> seqMap, String format, Object... arguments) {
        log.debug(buildMessage(seqMap, format), arguments);
    }

    /**
     * 带参数DEBUG级别信息
     * @param format 格式化信息
     * @param arguments 外部参数
     */
    public static void debug(String format, Object... arguments) {
        debug(null, format, arguments);
    }

    private static String buildMessage(LinkedHashMap<String, String> seqMap, String msg) {
        StringBuilder builder = new StringBuilder();
        // 主机
        builder.append("[").append(EnvConfig.HOSTNAME).append("]");
        // 调用方法
        StackTraceElement parentStack = getParentStack();
        builder.append("[").append(parentStack.getClassName()).append("]");
        builder.append("[").append(parentStack.getMethodName()).append("]");
        builder.append("[").append(parentStack.getLineNumber()).append("]");
        // 外部主键
        if (!MapUtils.isEmpty(seqMap)) {
           for (Map.Entry<String, String> entry : seqMap.entrySet()) {
               builder.append("[").append(entry.getKey()).append("=").append(entry.getValue()).append("]");
           }
        }
        // 消息主体
        builder.append(msg);        
        return builder.toString();
    }

    private static StackTraceElement getParentStack() {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stack.length; i++) {
            // 找到了外部调用类
            if (!CLASS_NAME.equals(stack[i].getClassName())) {
                return stack[i];
            }
        }
        throw new EarthBaseException("获取外部调用方法信息失败");
    }
}
