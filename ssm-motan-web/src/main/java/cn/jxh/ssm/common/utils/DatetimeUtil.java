package cn.jxh.ssm.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {

    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String TIME_PATTERN = "HH:mm:ss";

    public final static String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    public static Date String2Date(String dstr, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dstr);
        } catch (ParseException e) {
            return null;
        }
    }

}
