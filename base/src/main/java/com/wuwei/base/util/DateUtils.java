package com.wuwei.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    private static final String FORMAT_STRING = "yyyyMMddHHmm";

    public static String getOrderTimeForJd(int diff){
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.MINUTE, diff);
        return new SimpleDateFormat(FORMAT_STRING).format(temp.getTime());
    }
}
