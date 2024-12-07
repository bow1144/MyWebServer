package com.test.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动生成 Getters, Setters, equals, hashCode, toString 等
@NoArgsConstructor
@AllArgsConstructor
public class userStatistic {
    private double weeklyAttendanceRate;
    private double monthlyAttendanceRate;
    private double weeklyAvgTime;
    private double monthlyAvgTime;
}
