package com.wuwei.base.util;

public final class StringUtils {

    public static String EMPTY = "";

    public static boolean isNullOrEmpty(String input){
        return null == input || EMPTY.equals(input);
    }

}
