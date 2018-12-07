package com.wuwei.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    private static final String FORMAT_STRING = "yyyyMMddHHmm";

    public static String getOrderTimeForJd(){
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.MINUTE, -3);
        return new SimpleDateFormat(FORMAT_STRING).format(temp.getTime());
    }
}
