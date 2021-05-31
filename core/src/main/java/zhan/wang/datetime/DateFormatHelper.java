package zhan.wang.datetime;

import zhan.wang.constant.DateFormat;
import zhan.wang.log.MachSeqLogger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 日期格式 处理类
 */
public class DateFormatHelper {
    
    private DateFormatHelper() {
        // private constructor
    }
    
    /**
     * 获得一个DateFormatHelper实例（单例模式）
     * @return  实例
     */
    public static DateFormatHelper getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 格式化日期时间
     * @param date          日期
     * @param dateFormat    时间格式
     * @return  格式化后日期字符串
     */
    public String parseDateToStr(Date date, String dateFormat){
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.format(date);
        } catch (Exception e) {
            MachSeqLogger.warn("格式化日期时间[{}]出错", date, e);
            return null;
        }
    }
    
    /**
     * 格式化时间戳时间
     * @param timestamp     时间戳
     * @param timeFromat    时间格式
     * @return 格式化后时间字符串
     */
    public String parseTimestampToStr(Timestamp timestamp, String timeFromat){
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        try {
            return df.format(timestamp);
        } catch (Exception e) {
            MachSeqLogger.warn("格式化时间戳时间[{}]出错", timestamp, e);
            return null;
        }
    }

    /**
     * 日期字符串转化日期
     * 
     * @param dateStr       日期字符串
     * @param dateFormat    时间格式
     * @return  日期，有错返回null
     */
    public Date parseStringToDate(String dateStr, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            MachSeqLogger.warn("日期[{}]格式转换出错", dateStr, e);
            return null;
        }
    }
    
    /**
     * 把日期转换为纯年月日的日期
     * 
     * @param date  原日期
     * @return  转换后日期
     */
    public Date parseDateYMD(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DateFormat.DATE_FORMAT_DATE);
        try {
            return df.parse(df.format(date));
        } catch (Exception e) {
            MachSeqLogger.warn("日期[{}]格式转换出错", date, e);
            return null;
        }
    }
    
    private static class InstanceHolder {
        private static final DateFormatHelper instance = new DateFormatHelper();
    }

}
