package com.test.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class attendanceService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Map<LocalDate, List<String>> groupAttendanceByDate(List<LocalDateTime> attendance) {
        Map<LocalDate, List<String>> grouped = attendance.stream()
                .collect(Collectors.groupingBy(LocalDateTime::toLocalDate,
                        Collectors.mapping(time -> time.format(formatter), Collectors.toList())));

        // 填充每一天的记录，确保每一天有 6 个打卡记录
        for (Map.Entry<LocalDate, List<String>> entry : grouped.entrySet()) {
            List<String> times = entry.getValue();
            // 填充 null 以确保每一天有 6 条记录
            while (times.size() < 6) {
                times.add(null); // 填充 null
            }
        }
        return grouped;
    }

    //
    public static boolean isValid(LocalDateTime dateTime) {
        if(dateTime == null) return true;

        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();

        if ((hour == 8 && minute >= 30) ||
                (hour > 8 && hour < 11) ||
                (hour == 11 && minute <= 30)) return false;
        if ((hour == 14 && minute >= 30) ||
                (hour > 14 && hour < 17) ||
                (hour == 17 && minute <= 30)) return false;
        if ((hour == 19 && minute >= 30) ||
                (hour == 20) ||
                (hour == 21 && minute <= 30)) return false;
        return true;
    }

    public static Map<LocalDate, List<Boolean>> checkAttendanceValidity(List<LocalDateTime> attendance) {
        return attendance.stream()
                .collect(Collectors.groupingBy(
                        LocalDateTime::toLocalDate,  // 按照日期分组
                        Collectors.mapping(
                                attendanceService::isValid,  // 判断每个考勤时间是否有效
                                Collectors.toList()  // 将结果收集为一个列表
                        )
                ));
    }
}
