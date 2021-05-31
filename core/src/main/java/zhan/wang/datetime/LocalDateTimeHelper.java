package zhan.wang.datetime;


import zhan.wang.constant.DateFormat;
import zhan.wang.exception.EarthBaseException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 
 * LocalDateTime 处理类
 */
public class LocalDateTimeHelper {

    private static final String DEFAULT_ZONE = "Asia/Shanghai";
    private static final ZoneOffset DEF_ZONEOFFSET = ZoneOffset.of("+8");

    private LocalDateTimeHelper() {
        // private constructor
    }
    
    /**
     * 获得一个LocalDateTimeHelper实例（单例模式）
     * @return LocalDateTimeHelper 实例
     */
    public static LocalDateTimeHelper getInstance() {
        return InstanceHolder.instance;
    }
    
    /**
     * 时间格式化
     *
     * @param datetime {@link LocalDateTime}
     * @param pattern  时间格式模板
     * @see zhan.wang.constant.DateFormat
     * 
     * @return 格式化后时间字符串
     */
    public static String format(LocalDateTime datetime, String pattern) {
        return getInstance().pvtFormat(datetime, pattern);
    }
    
    /**
     * 格式化时间为默认日期时间(yyyy-MM-dd HH:mm:ss)
     * 
     * @param datetime  {@link LocalDateTime}
     * @return 格式化后时间字符串
     */
    public static String formatDefault(LocalDateTime datetime) {
        return getInstance().pvtFormat(datetime, DateFormat.DATE_FORMAT_TIMESTAMP);
    }
    
    /**
     * 格式化时间为默认日期时间精确到毫秒(yyyyMMddHHmmssSSS)
     *
     * @param datetime  {@link LocalDateTime}
     * @return 格式化后时间字符串
     */
    public static String formatDefaultMilli(LocalDateTime datetime) {
        return getInstance().pvtFormat(datetime, DateFormat.DATE_FORMAT_TIMESTAMP_MIS);
    }
    
    private String pvtFormat(LocalDateTime datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return datetime.format(formatter);
    }
    
    /**
     * 获取毫秒数，指定时区
     *
     * @param datetime {@link LocalDateTime}
     * @param zoneId   时区
     * @return  毫秒值
     */
    public static long getMilliseconds(LocalDateTime datetime, String zoneId) {
        return getInstance().pvtGetMilliseconds(datetime, zoneId);
    }
    
    /**
     * 获取毫秒数，默认时区
     * 
     * @param datetime  {@link LocalDateTime}
     * @return  毫秒值
     */
    public static long getDefaultMilliseconds(LocalDateTime datetime) {
        return getInstance().pvtGetMilliseconds(datetime, DEFAULT_ZONE);
    }
    
    private long pvtGetMilliseconds(LocalDateTime datetime, String zoneId) {
        if (datetime != null) {
            return datetime.atZone(ZoneId.of(zoneId)).toInstant().toEpochMilli();
        } else {
            throw new EarthBaseException("输入时间值不能为空");
        }
    }
    
    /**
     * 解析时间字符串
     * 
     * @param datetimeStr   时间字符串
     * @param pattern  时间格式模板
     * @see zhan.wang.constant.DateFormat
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parseDateTime(String datetimeStr, String pattern) {
        return getInstance().pvtParseDateTime(datetimeStr, pattern);
    }
    
    private LocalDateTime pvtParseDateTime(String datetimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetimeStr, formatter);
    }
    
    /**
     * 转化为 时间类型，默认时区
     * 
     * @param datetime  {@link LocalDateTime}
     * @return  {@link Date}
     */
    public static Date toDate(LocalDateTime datetime) {
        return getInstance().pvtToDate(datetime, DEFAULT_ZONE);
    }
    
    /**
     * 转化为 时间类型，指定时区
     * 
     * @param datetime  {@link LocalDateTime}
     * @param zoneId    时区
     * @return  {@link Date}
     */
    public static Date toDate(LocalDateTime datetime, String zoneId) {
        return getInstance().pvtToDate(datetime, zoneId);
    }
    
    private Date pvtToDate(LocalDateTime datetime, String zoneId) {
        return Date.from(datetime.atZone(ZoneId.of(zoneId)).toInstant());
    }
    
    /**
     * 转化为本地时间类型，默认时区
     *
     * @param date  {@link Date}
     * @return  {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return getInstance().pvtToLocalDateTime(date);
    }
    
    private LocalDateTime pvtToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.of(DEFAULT_ZONE)).toLocalDateTime();
        return localDateTime;
    }
    
    /**
     * 获得起始时间秒数
     * @return  起始时间
     */
    public static long getStartTime() {
        return LocalDateTime.now().toEpochSecond(DEF_ZONEOFFSET);
    }
    
    /**
     * 获得经过了多少时间秒数
     * @param startTime 起始时间秒数
     * @return  经过了多少秒
     */
    public static long getDuration(long startTime) {
        Long endTime = LocalDateTime.now().toEpochSecond(DEF_ZONEOFFSET);
        return endTime - startTime;
    }
    
    /**
     * 获取时间偏移量
     * 
     * @param minutes   时差（以分钟计算）
     * @return  {@link ZoneOffset}
     */
    public static ZoneOffset getZoneOffsetByMinute(int minutes) {
        return ZoneOffset.ofTotalSeconds(minutes * 60);
    }
    
    private static class InstanceHolder {
        private static final LocalDateTimeHelper instance = new LocalDateTimeHelper();
    }
}
