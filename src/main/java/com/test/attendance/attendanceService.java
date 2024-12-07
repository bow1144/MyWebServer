package com.test.attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

    public static double getWeeklyAttendanceRate(List<LocalDateTime> attendance) {
        int size = attendance.size();
        if(size > 42) attendance = attendance.subList(size - 42, size);
        long validDayNum = attendance.stream().filter(attendanceService::isValid).count();
        long dayNum = attendance.size();
        return (double) validDayNum / dayNum;
    }

    public static double getMonthlyAttendanceRate(List<LocalDateTime> attendance) {
        long validDayNum = attendance.stream().filter(attendanceService::isValid).count();
        long dayNum = attendance.size();
        return (double) validDayNum / dayNum;
    }

    public static double getAverageWorkTime4Last7Days(List<LocalDateTime> attendance) {
        // 1. 按天分组
        Map<LocalDate, List<LocalDateTime>> attendanceByDate = attendance.stream()
                .collect(Collectors.groupingBy(LocalDateTime::toLocalDate));

        // 2. 获取最近7天的日期
        List<LocalDate> last7Days = getLastNDays(attendanceByDate, 7);

        // 3. 计算每天的工作时间
        List<Double> dailyWorkTimes = new ArrayList<>();
        for (LocalDate date : last7Days) {
            List<LocalDateTime> times = attendanceByDate.get(date);
            if (times != null && times.size() == 6) {
                // 计算时间差并求和
                double workTime = calculateWorkTime(times);
                dailyWorkTimes.add(workTime);
            }
        }

        // 4. 计算平均工作时间
        return dailyWorkTimes.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public static double getAverageWorkTime4Last30Days(List<LocalDateTime> attendance) {
        // 1. 按天分组
        Map<LocalDate, List<LocalDateTime>> attendanceByDate = attendance.stream()
                .collect(Collectors.groupingBy(LocalDateTime::toLocalDate));

        // 2. 获取最近30天的日期
        List<LocalDate> last30Days = getLastNDays(attendanceByDate, 30);

        // 3. 计算每天的工作时间
        List<Double> dailyWorkTimes = new ArrayList<>();
        for (LocalDate date : last30Days) {
            List<LocalDateTime> times = attendanceByDate.get(date);
            if (times != null && times.size() == 6) {
                // 计算时间差并求和
                double workTime = calculateWorkTime(times);
                dailyWorkTimes.add(workTime);
            }
        }

        // 4. 计算平均工作时间
        return dailyWorkTimes.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    private static List<LocalDate> getLastNDays(Map<LocalDate, List<LocalDateTime>> attendanceByDate, int n) {
        List<LocalDate> allDates = new ArrayList<>(attendanceByDate.keySet());
        Collections.sort(allDates, Collections.reverseOrder()); // 按照日期倒序
        return allDates.stream().limit(n).collect(Collectors.toList());
    }

    // 计算一天的工作时间（6个打卡点时间差求和）
    private static double calculateWorkTime(List<LocalDateTime> times) {
        // 假设 times 中包含了 6 个时间点
        Duration workDuration = Duration.ZERO;
        for (int i = 0; i < 5; i += 2) { // 每次计算相邻两个时间点的差
            workDuration = workDuration.plus(Duration.between(times.get(i), times.get(i + 1)));
        }
        return workDuration.toMinutes() / 60.0; // 转换为小时
    }

}
