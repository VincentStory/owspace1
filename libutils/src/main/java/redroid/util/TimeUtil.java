package redroid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TODO
 *
 * @author zhangzheming created at 2018-02-09 17:39.
 */
public class TimeUtil {

    private static Date string2Date(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(String str) {
        if (str != null){
            return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(string2Date(str));
        }else{
            return "";
        }

    }

    public static String getDate(String string){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(string2Date(string));
    }

    /**
     * 获取基于基准日期经过一定偏移量的日期
     * @param baseLineDate      基准日期
     * @param amount            偏移量， 例如: 获取明日日期，则值为1， 获取昨天日期，则值为-1.
     * @return                  偏移后的日期
     */
    public static Date getOffsetDate(Date baseLineDate, int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(baseLineDate);
        calendar.add(Calendar.DATE,amount);
        return calendar.getTime();
    }
}
