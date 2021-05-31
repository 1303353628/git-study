package zhan.wang.moon.datetime;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import zhan.wang.moon.constant.DateFormat;

import java.text.ParseException;
import java.util.*;

/**
 * 
 * java.util.Date 处理类
 */
public class DateHelper {
    
    private DateFormatHelper dfHelper = DateFormatHelper.getInstance();

    private DateHelper() {
        // private constructor
    }
    
    /**
     * 获得一个DateHelper实例（单例模式）
     * @return  实例
     */
    public static DateHelper getInstance() {
        return InstanceHolder.instance;
    }
    
    /**
     * 解析时间字符串
     * 
     * @param dateStr   时间字符串
     * @param pattern   时间格式模板
     * @see DateFormat
     * @return  {@link Date}
     * @throws ParseException   格式转换异常
     */
    public Date parseDate(String dateStr, String pattern) throws ParseException {
        return dfHelper.parseStringToDate(dateStr, pattern);
    }
    
    /**
     * 获取某日期的年份
     * @param date  日期时间
     * @return  年份
     */
    public int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取某日期的月份
     * @param date  日期时间
     * @return  月份
     */
    public int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得某日期的MM格式月份
     * @param date  日期时间
     * @return  两位月份
     */
    public static String getMonthMM(Date date) {
        int mon = getInstance().getMonth(date);
        if (mon < 10) {
            return "0" + mon;
        } else {
            return String.valueOf(mon);
        }
    }

    /**
     * 获取某日期的天数
     * @param date  日期时间
     * @return  天数
     */
    public int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);// 获取日
    }
    
    /**
     *  获取某日期的DD格式天数
     * @param date  日期时间
     * @return  两位天数
     */
    public static String getDayDD(Date date) {
        int day = getInstance().getDay(date);
        if (day < 10) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }
    
    /**
     * 获得当前时间yyyy-MM-dd的值
     * @return 10位日期时间戳
     */
    public String getDateStandard() {
        Calendar cal = Calendar.getInstance();
        return dfHelper.parseDateToStr(cal.getTime(), DateFormat.DATE_FORMAT_DATE);
    }
    
    /**
     * 获得当前时间yyyy-MM-dd HH:mm:ss的值
     * @return 19位时间戳
     */
    public String getTimestampStandard() {
        Calendar cal = Calendar.getInstance();
        return dfHelper.parseDateToStr(cal.getTime(), DateFormat.DATE_FORMAT_TIMESTAMP);
    }
    
    /**
     * 获得当前时间yyyyMMddHHmmss的值
     * @return 14位时间戳
     */
    public String getTimestampInSecond() {
        Calendar cal = Calendar.getInstance();
        return dfHelper.parseDateToStr(cal.getTime(), DateFormat.DATE_FORMAT_TIMESTAMP14);
    }
    
    /**
     * 获得当前时间yyyyMMddHHmmssSSS的值
     * @return  17位时间戳
     */
    public String getTimestampInMillSecond() {
        Calendar cal = Calendar.getInstance();
        return dfHelper.parseDateToStr(cal.getTime(), DateFormat.DATE_FORMAT_TIMESTAMP_MIS);
    }
    
    /**
     * 计算两个日期相差整数天数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return  相差整数天
     */
    public static int getBetweenDays(Date startTime, Date endTime) {
        return getInstance().pvtGetBetweenDays(startTime, endTime);
    }
    
    private int pvtGetBetweenDays(Date startTime, Date endTime) {
        Date startDate = dfHelper.parseDateYMD(startTime);
        Date endDate = dfHelper.parseDateYMD(endTime);
        if (startDate != null && endDate != null) {
            Long delta = (endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24);
            return delta.intValue();
        }
        return 0;
    }
    
    /**
     * 比较前一个日期不晚于后一个日期
     * @param firstDate     第一个日期（yyyy-MM-dd）
     * @param secondDate    第二个日期（yyyy-MM-dd）
     * @return  前一个日期不晚于后一个日期
     */
    public static boolean compareTo(String firstDate, String secondDate) {
        return getInstance().pvtCompareTo(firstDate, secondDate);
    }
    
    private boolean pvtCompareTo(String firstDate, String secondDate) {
        // 字符串为空，直接返回
        if (StringUtils.isEmpty(firstDate) || StringUtils.isEmpty(secondDate)) {
            return true;
        }
        Date first = dfHelper.parseStringToDate(firstDate, DateFormat.DATE_FORMAT_DATE);
        Date second = dfHelper.parseStringToDate(secondDate, DateFormat.DATE_FORMAT_DATE);
        if (first != null && second != null) {
            return first.compareTo(second) <= 0;
        } else {
            return true;
        }
    }
    
    /**
     * 获得列表中最新的日期
     * @param list  日期列表
     * @return  最新的日期
     */
    public static String getLatestDate(final List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        
        List<String> temp = new ArrayList<>();
        temp.addAll(list);
        Collections.sort(temp);
        return temp.get(temp.size() - 1);
    }
    
    /**
     * 获得几年前的日期
     * @param date  基准日期
     * @param year  年限，可以为x.5年
     * @return  几年前的日期
     */
    public static Date getPreviousDate(Date date, double year) {
        return getInstance().pvtGetPreviousDate(date, year);
    }
    
    private Date pvtGetPreviousDate(Date date, double year) {
        Double month = year * 12;
        int mon = month.intValue(); // 取整数月
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONDAY, -mon);
        return dfHelper.parseDateYMD(calendar.getTime());
    }

    private static class InstanceHolder {
        private static final DateHelper instance = new DateHelper();
    }
}
