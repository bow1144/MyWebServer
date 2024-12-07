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
}
