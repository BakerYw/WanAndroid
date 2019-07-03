package com.nyw.wanandroid.utils;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author BakerJ
 * @date 2018/3/26
 */

public class DateUtil {
    public static String getCircleDisplayDate(long date) {
        return getCircleDisplayDate(TimeUtils.date2String(new Date(date), new SimpleDateFormat
                ("yyyy-MM-dd HH:mm:ss", Locale.CHINESE)));
    }

    public static String getEventDisplayDate(long date) {
        return getCircleDisplayDate(TimeUtils.date2String(new Date(date), new SimpleDateFormat
                ("yyyy-MM-dd HH:mm", Locale.CHINESE)));
    }

    public static String getCircleDisplayDate(String date) {
        if (null == date) {
            return "";
        }
        try {
            String patten = "yyyy-MM-dd HH:mm:ss";
            int length = date.length();
            if (length == 10) {
                patten = "yyyy-MM-dd";
            } else if (length == 16) {
                patten = "yyyy-MM-dd HH:mm";
            } else if (length == 19) {
                patten = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat format = new SimpleDateFormat(patten, Locale.CHINESE);
            Calendar calDate = Calendar.getInstance();
            Date destDate = TimeUtils.string2Date(date, format);
            calDate.setTime(destDate);
            Calendar calNow = Calendar.getInstance();
            calNow.setTime(new Date());
            SimpleDateFormat destFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINESE);
            String showTime = TimeUtils.date2String(destDate, destFormat);
            boolean sameYear = calDate.get(Calendar.YEAR) == calNow.get(Calendar.YEAR);
            if (sameYear) {
                boolean sameDay = calDate.get(Calendar.MONTH) == calNow.get(Calendar.MONTH) &&
                        calDate.get(Calendar.DAY_OF_MONTH) == calNow.get(Calendar.DAY_OF_MONTH);
                if (sameDay) {
                    int hourDelta = calNow.get(Calendar.HOUR_OF_DAY) - calDate.get(Calendar
                            .HOUR_OF_DAY);
                    if (hourDelta < 1) {
                        int minuteDelta = calNow.get(Calendar.MINUTE) - calDate.get(Calendar
                                .MINUTE);
                        if (minuteDelta == 0) {
                            showTime = "刚刚";
                        } else {
                            showTime = minuteDelta + "分钟前";
                        }
                    } else if (hourDelta <= 2) {
                        showTime = hourDelta + "小时前";
                    } else {
                        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm", Locale.CHINESE);
                        showTime = "今天" + TimeUtils.date2String(destDate, formatTime);
                    }
                }
            }
            return showTime;
        } catch (Exception ignore) {
            return date;
        }
    }

    public static String getEventDisplayDate(String startDate, String expireDate) {
        if (startDate == null || expireDate == null) {
            return "";
        }
        try {
            String patten = "yyyy-MM-dd HH:mm:ss";
            int length = startDate.length();
            if (length == 10) {
                patten = "yyyy-MM-dd";
            } else if (length == 16) {
                patten = "yyyy-MM-dd HH:mm";
            }
            SimpleDateFormat format = new SimpleDateFormat(patten, Locale.CHINESE);
            Date start = TimeUtils.string2Date(startDate, format);
            Date expire = TimeUtils.string2Date(expireDate, format);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(start);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(expire);
            SimpleDateFormat destFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINESE);
            boolean sameYear = calStart.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR);
            if (sameYear) {
                boolean sameDay = calStart.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH) &&
                        calStart.get(Calendar.DAY_OF_MONTH) == calEnd.get(Calendar.DAY_OF_MONTH);
                if (sameDay) {
                    return TimeUtils.date2String(start, destFormat);
                }
            }
            return TimeUtils.date2String(start, destFormat) + " — " + TimeUtils.date2String(expire,
                    destFormat);
        } catch (Exception ignore) {
            return startDate + "-" + expireDate;
        }
    }

    public static String getExpDisplayDate(String startDate, String expireDate) {
        if (startDate == null || expireDate == null) {
            return "";
        }
        try {
            String patten = "yyyy-MM-dd HH:mm:ss";
            int length = startDate.length();
            if (length == 10) {
                patten = "yyyy-MM-dd";
            } else if (length == 16) {
                patten = "yyyy-MM-dd HH:mm";
            }
            SimpleDateFormat format = new SimpleDateFormat(patten, Locale.CHINESE);
            Date start = TimeUtils.string2Date(startDate, format);
            Date expire = TimeUtils.string2Date(expireDate, format);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(start);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(expire);
            SimpleDateFormat destFormat = new SimpleDateFormat("M月d日", Locale.CHINESE);
            boolean sameYear = calStart.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR);
            if (sameYear) {
                boolean sameDay = calStart.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH) &&
                        calStart.get(Calendar.DAY_OF_MONTH) == calEnd.get(Calendar.DAY_OF_MONTH);
                if (sameDay) {
                    return TimeUtils.date2String(start, destFormat);
                }
            }
            return TimeUtils.date2String(start, destFormat) + " — " + TimeUtils.date2String(expire,
                    destFormat);
        } catch (Exception ignore) {
            return startDate + "-" + expireDate;
        }
    }

    public static String getExpDisplayDate(String date) {
        if (date == null) {
            return "";
        }
        try {
            String patten = "yyyy-MM-dd HH:mm:ss";
            int length = date.length();
            if (length == 10) {
                patten = "yyyy-MM-dd";
            } else if (length == 16) {
                patten = "yyyy-MM-dd HH:mm";
            }
            SimpleDateFormat format = new SimpleDateFormat(patten, Locale.CHINESE);
            Date start = TimeUtils.string2Date(date, format);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(start);
            SimpleDateFormat destFormat = new SimpleDateFormat("M月d日 HH:mm", Locale.CHINESE);
            return TimeUtils.date2String(start, destFormat);
        } catch (Exception ignore) {
            return date;
        }
    }
}
