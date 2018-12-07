package com.wuwei.base.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class CollectionUtils {

    public static boolean isNullOrEmpty(Collection collection){
        return (null == collection || collection.isEmpty());
    }

    public static boolean isNullOrEmpty(Object[] objects){
        return (null == objects || objects.length <= 0);
    }

    public static String join(List<Integer> objs, String separator){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(Iterator iterator = objs.iterator(); iterator.hasNext(); stringBuffer.append(String.valueOf(iterator.next()))) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(separator);
            }
        }
        return stringBuffer.toString();
    }

}
