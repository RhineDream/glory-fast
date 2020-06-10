package top.glory.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * yyyyMMddHHmmss
     * @param fmt
     * @return
     */
    public static String getCurrentDateStr(String fmt) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
        return simpleDateFormat.format(date);
    }

}
