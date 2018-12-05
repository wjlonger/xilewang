package com.wuwei.base.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class CollectionUtil {

    public static boolean isNullOrEmpty(Collection collection){
        return (null == collection || collection.isEmpty());
    }

    public static String join(List<Integer> objs, String separator){
        if(isNullOrEmpty(objs)){
            return StringUtil.EMPTY;
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
