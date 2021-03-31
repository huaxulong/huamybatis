package com.huaxu.minimybatis.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @description: CommonUtis
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 8:26 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public final class CommonUtis {

    /**
     * string is not empty
     *
     * @param src
     * @return
     */
    public static boolean isNotEmpty(String src)
    {
        return src != null && src.trim().length() > 0;
    }

    /**
     * list/set is not empty
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection)
    {
        return collection != null && !collection.isEmpty();
    }

    /**
     * map is not empty
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return map != null && !map.isEmpty();
    }

    /**
     * 数组不为空
     *
     * @param arr
     * @return
     * @see
     */
    public static boolean isNotEmpty(Object[] arr)
    {
        return arr != null && arr.length > 0;
    }

    /**
     * 对字符串去空白符和换行符等
     *
     * @return
     */
    public static String stringTrim(String src)
    {
        return (null != src) ? src.trim() : null;
    }

}
