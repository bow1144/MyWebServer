package com.test.timeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // 获取当前时间并格式化为字符串
    public static String getFormattedCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();  // 获取当前时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);  // 格式化为字符串
    }

    // 获取当前的 LocalDateTime 类型
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();  // 获取当前时间，不格式化
    }
}
