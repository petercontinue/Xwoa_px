package com.xwcloud.cloud.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 */
public class DateUtil {

    public static Date getNowDate() {
        Date date = new Date();
        return date;
    }

    public static String formatDate1(Date myDate) {
        return formatDate(myDate, "yyyy年MM月dd日 HH时mm分ss秒");
    }

    public static String formatDate2(Date myDate) {
        return formatDate(myDate, "yyyy年MM月dd日");
    }

    public static String formatDate3(Date myDate) {
        return formatDate(myDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate4(Date myDate) {
        return formatDate(myDate, "yyyy-MM-dd");
    }

    public static String formatDate5(Date myDate) {
        return formatDate(myDate, "yyyy/MM/dd");
    }

    public static String formatDate9(Date myDate) {
        return formatDate(myDate, "yyyy年MM月dd日 HH时mm分");
    }

    public static String formatDate10(Date myDate) {
        return formatDate(myDate, "yyyy-MM-dd HH:mm");
    }

    public static String formatDate11(Date myDate) {
        return formatDate(myDate, "yyyyMMddHHmmss");
    }

    public static String formatDate12(Date myDate) {
        return formatDate(myDate, "yyyyMMdd");
    }

    public static void main(String[] args) {
        System.out.println(formatDate11(new Date()));
    }

    public static String formatDate(Date myDate, String formatStr) {
        if (myDate == null) {
            return "";
        }
        String strDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            strDate = formatter.format(myDate);
        } catch (Exception ex) {
        }
        return strDate;
    }

    public static int getYear() {
        Date dt = new Date();
        return dt.getYear() + 1900;
    }

    public static int getMonth() {
        Date dt = new Date();
        return dt.getMonth();
    }

    public static int getDay() {
        Date dt = new Date();
        return dt.getDay();
    }

    public static String formatDate6(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");

        String strDate = formatter.format(myDate);

        return strDate;
    }

    public static String getNowYear() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String time = format.format(date);
        return time;
    }

    public static String formatDate7(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        String strDate = formatter.format(myDate);

        return strDate;
    }

    public static String formatDate8(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时");

        String strDate = formatter.format(myDate);

        return strDate;
    }

    /**
     * @param time1 时间1 例如date1.getTime()
     * @param time2 时间2 例如date2.getTime()
     * @return
     */
    public static long calDifference(long time1, long time2, int field) {
        try {
            if (time1 == time2) {
                return 0;
            } else if (time1 > time2) {
                // 确保time1比time2小   
                return calDifference(time2, time1, field);
            }
            if (field == Calendar.MILLISECOND) {
                long t = time2 - time1;
                return t;
            }
            // 下面清除不要参与比较的内容   
            Calendar cal1 = Calendar.getInstance();
            cal1.setLenient(false);
            cal1.setTimeInMillis(time1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setLenient(false);
            cal2.setTimeInMillis(time2);
            for (int x = 0; x < Calendar.FIELD_COUNT; x++) {
                if (x > field) {
                    cal1.clear(x);
                    cal2.clear(x);
                }
            }
            // 重新设定初始值   
            time1 = cal1.getTimeInMillis();
            time2 = cal2.getTimeInMillis();
            long ms = 0;
            int min = 0;// 下限,从0开始   
            int max = 1;// 每次所加的值,第一次加1   
            while (true) {
                // 因为max的值都是相对time1而言,故每次都是从time1开始而不是ms   
                cal1.setTimeInMillis(time1);
                cal1.add(field, max);// 将field对应的字段加上max的值   
                ms = cal1.getTimeInMillis();
                if (ms == time2) {
                    // 两个时间之间相差的值为max   
                    min = max;
                    break;
                } else if (ms > time2) {
                    // 超过后,则差值肯定小于max   
                    break;
                } else {
                    // 仍然小于time2,继续增大max,直到ms>=time2为止   
                    max <<= 1;
                }
            }
            // 上面的操作中没有找到准确的值,接下来使用二分查找以准确找出差值   
            while (max > min) {
                cal1.setTimeInMillis(time1);
                int t = (min + max) >>> 1;
                cal1.add(field, t);
                ms = cal1.getTimeInMillis();
                if (ms == time2) {
                    min = t;
                    break;
                } else if (ms > time2) {
                    max = t;
                } else {
                    min = t;
                }
            }
            return min;
        } catch (Exception ex) {
            return 0L;
        }
    }

    /**
     * @param time1 时间1 例如date1.getTime()
     * @param time2 时间2 例如date2.getTime()
     * @param abs   为true求绝对值
     * @return time1<time2 返回正 ， 否则返回负
     */
    public static long calDifference(long time1, long time2, int field, boolean abs) {
        try {

            long tt = calDifference(time1, time2, field);
            if (time1 > time2) {
                return -tt;
            }
            return tt;
        } catch (Exception ex) {
            return 0L;
        }
    }

    /**
     * @param date
     * @param days  传入的是正值为+，负值为1
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * @param value
     * @param format
     * @return
     */
    public static Date toDate(String value, String format) {
        SimpleDateFormat d2 = new SimpleDateFormat(format);
        try {
            return d2.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param value
     * @param format
     * @return
     */
    public static String toFormatString(Date value, String format) {
        SimpleDateFormat d2 = new SimpleDateFormat(format);
        return d2.format(value);
    }

    public static String getNowString() {
        Timestamp tnow = new Timestamp(System.currentTimeMillis());
        return tnow.toString();
    }

    public static Timestamp getTSDayStart(Timestamp tsp) {
        String dss = toFormatString(tsp, "yyyy-MM-dd") + " 00:00:00";
        Date d = toDate(dss, "yyyy-MM-dd HH:mm:ss");
        return new Timestamp(d.getTime());
    }

    public static Timestamp getTSDayEnd(Timestamp tsp) {
        String dss = toFormatString(tsp, "yyyy-MM-dd") + " 23:59:59";
        Date d = toDate(dss, "yyyy-MM-dd HH:mm:ss");
        return new Timestamp(d.getTime());
    }

    public static Date getInDate1(String inDate) {
        try {
            Calendar cal = Calendar.getInstance();
            int y = Integer.parseInt(inDate.substring(0, 4));
            int M = Integer.parseInt(inDate.substring(4, 6));
            M--;
            int d = Integer.parseInt(inDate.substring(6, 8));
            int h = Integer.parseInt(inDate.substring(8, 10));
            int m = Integer.parseInt(inDate.substring(10, 12));
            int s = Integer.parseInt(inDate.substring(12));
            cal.set(y, M, d, h, m, s);
            return cal.getTime();
        } catch (Exception ex) {

        }
        return null;
    }

    public static String getCoverTime(String time) {
        String coverTime = time.replace("-", "").replace(":", "").replace(" ", "");
        return coverTime;
    }


    /**
     * @Description: getWeekOfDate()方法作用:获取输入日期周几
     * @param:[date]
     * @return:java.lang.String
     * @auter:yyl
     * @data:2020/12/15 17:05
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * @Description: getAgeByBirth()方法作用:计算年龄
     * @param:[birthDay]
     * @return:int
     * @auter:yyl
     * @data:2021/1/25 11:33
     */
    public static int getAgeByBirth(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning(Date Ym) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Ym);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight(Date Ym) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Ym);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }


}