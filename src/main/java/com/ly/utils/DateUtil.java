package com.ly.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : ly.
 * @Date : 2018/5/11.
 */
public class DateUtil {


    public static final String DEFAULT_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化日期
     * @param dateValue
     * @return
     */
    public static String dateToString(Date dateValue) {
        return dateToString(dateValue,DEFAULT_DATE_TYPE);
    }

    /**
     * @Title: dateToString
     * @Description: 将时间转换为字符串
     * @param dateValue 需要转换的日期
     * @param strFormat 需要转换的日期格式
     * 					例如(yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
     * @return String
     */
    public static String dateToString(Date dateValue, String strFormat) {
        SimpleDateFormat clsFormat = new SimpleDateFormat(strFormat);
        return clsFormat.format(dateValue);
    }
    /**
     * @Title: stringToDate
     * @Description: 将字符串转换为date类型
     * @param strValue
     * @param dateformat 需要转换的日期格式
     * 					   例如：(yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
     * @return Date
     */
    public static Date stringToDate(String strValue, String dateformat) {
        if (StringUtils.isEmpty(strValue)) {
            return null;
        }
        SimpleDateFormat clsFormat = new SimpleDateFormat(dateformat);
        ParsePosition pos = new ParsePosition(0);
        return clsFormat.parse(strValue, pos);
    }

    public static Date stringToDate(String strValue) {
        return stringToDate(strValue, DEFAULT_DATE_TYPE);
    }

    //todo  timestamp


    /**
     * @Title: getSysOptDate
     * @Description: 获得系统的当前时间
     * @param strFormat 例如：需要转换的日期格式  (yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
     * @return String 根据传入的数据类型返回日期字符串

     */
    public static String getSysOptDate(String strFormat) {
        Calendar date = Calendar.getInstance();
        Date sysDate = date.getTime();
        String optDate="";
        if (StringUtils.isEmpty(strFormat)) {
            optDate = dateToString(sysDate, "yyyy-MM-dd HH:mm:ss");
        } else{
            optDate = dateToString(sysDate, strFormat);
        }
        return optDate;
    }


    /**
     *
     * @Title: formatDate
     * @Description: 对日期时间进行格式化去掉后面的时间信息
     * @param date  要格式化的时间
     * @throws ParseException
     */
    public static Date formatDate(Date date) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=sdf.parse(sdf.format(date));
        return date;
    }

    /**
     * 距离0点的秒数
     * @return
     */
    public static int getTodayRemainSeconds(){
        try {
            Date now = new Date();

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            calendar.add(Calendar.DATE,1);
            Date tomorrow = calendar.getTime();

            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
            Date tbegainDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString + " 00:00:00");

            return (int) ((tbegainDate.getTime() - now.getTime()) / 1000);

        } catch (ParseException e) {
        }

        return 0;
    }

    /**
     * 查看日期是一周的第几天
     * @param date
     * @return
     */
    public static int dayForWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 将long型时间转成指定格式时间
     * @param format
     * @param time
     * @return
     */
    public static String parseLongDate(String format, Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

    /**
     * 对日期字符串进行排序
     * @param dateList
     */
    public static void sortTextDate(List<String> dateList) {
        Collections.sort(dateList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareDate(o1, o2);
            }
        });
    }

    public static void sort(List<Date> dateList) {
        dateList.sort(DateUtil::compareDate);
    }

    /**
     * 如果需要比较的时间字符串都为空，则返回 -2
     * @param d1
     * @param d2
     * @return
     */
    public static int compareDate(String d1, String d2) {
        if (StringUtils.isBlank(d1) || StringUtils.isBlank(d2)) {
            return -2;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(stringToDate(d1));
        Calendar c2 = Calendar.getInstance();
        c2.setTime(stringToDate(d2));
        return compareDate(c1, c2);
    }

    public static int compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return compareDate(c1, c2);
    }

    public static int compareDate(Calendar c1, Calendar c2) {
        int yearC1 = c1.get(Calendar.YEAR);
        int yearC2 = c2.get(Calendar.YEAR);
        if (yearC1 == yearC2) {
            int monthC1 = c1.get(Calendar.MONTH);
            int monthC2 = c2.get(Calendar.MONTH);
            if (monthC1 == monthC2) {
                int dayC1 = c1.get(Calendar.DAY_OF_MONTH);
                int dayC2 = c2.get(Calendar.DAY_OF_MONTH);
                if (dayC1 == dayC2) {
                    return 0;
                }
                return dayC1 > dayC2 ? 1 : -1;
            }
            return monthC1 > monthC2 ? 1 : -1;
        }
        return yearC1 > yearC2 ? 1 : -1;
    }

    /**
     * @Title: daysBetweenTwoDate
     * @Description:两个时间之间的天数间隔
     * @param smdate  小的时间
     * @param bdate   大的时间
     * @throws ParseException
     * @return int 相隔的天数
     */
    public static int daysBetweenTwoDate(Date smdate,Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 获得从今天到指定日期时间的剩余天数
     * @param endTime
     * @return
     */
    public static int getLeftDay(Date endTime){
        return (int) ((endTime.getTime() -System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获得从开始时间到结束时间的天数
     * @param startTime
     * @param endTime
     * @return
     */
    public static int offsetDay(Date startTime,Date endTime){
        return (int) ((endTime.getTime() -startTime.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static Date getWeekStartByLocal(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.add(Calendar.DAY_OF_YEAR, 1);
        return getDayStart(c.getTime());
    }

    public static Date getWeekEndByLocal(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.DAY_OF_WEEK, 7);
        c.add(Calendar.DAY_OF_YEAR, 1);
        return getDayEnd(c.getTime());
    }

    public static Date getMonthStart(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStart(c.getTime());
    }

    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return getDayEnd(c.getTime());
    }

    public static Date getDayStart(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static String compareToTime(Date date1, Date date2, boolean gainBig,String format){
        Date resultDate = compareToTime(date1, date2, gainBig);
        return dateToString(resultDate,format);
    }

    public static Date compareToTime(Date date1, Date date2, boolean gainBig) {
        boolean isFirstSmall = greaterThenTime(date1, date2);
        if (isFirstSmall) {
            if (gainBig) {
                return date2;
            } else {
                return date1;
            }
        } else {
            if (gainBig) {
                return date1;
            } else {
                return date2;
            }
        }
    }

    public static boolean greaterThenTime(Date referDate, Date comDate) {
        return referDate.getTime() < comDate.getTime();
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        System.out.println(dateToString(time));

        int i = dayForWeek(time);
        System.out.println(i);

        String s = parseLongDate(DEFAULT_DATE_TYPE, System.currentTimeMillis());
        System.out.println(s);
    }



}
