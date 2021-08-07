package org.example.shoptemp.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author czx
 * @date 2021/8/7
 */
public class StringUtil {
    /**
     * 分隔字符串
     * @param str
     * @param delimiter 分隔符
     * @return
     */
    public static List<Integer> split2IntegerList(String str, String delimiter) {
        return Arrays.stream(str.split(delimiter))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
