package com.nyw.libproject.util;

import java.util.Formatter;

public class StringUtils {
    // 将1 转换成0.01
    public static String formatMoney(String amount) {
        double value = Integer.valueOf(amount) / 100d;
        return new Formatter().format("%.2f", value).toString();
    }
}
