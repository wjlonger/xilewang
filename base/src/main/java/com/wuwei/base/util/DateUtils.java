package com.wuwei.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    private static final String FORMAT_STRING = "yyyyMMddHHmm";

    private static final SimpleDateFormat DATE_FORMAT_FOR_JD = new SimpleDateFormat(FORMAT_STRING);

    public static String getOrderTimeForJd(){
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.MINUTE, -3);
        return DATE_FORMAT_FOR_JD.format(temp.getTime());
    }
}
