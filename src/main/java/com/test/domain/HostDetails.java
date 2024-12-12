package com.test.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data // 自动生成 Getters, Setters, equals, hashCode, toString 等
@NoArgsConstructor
@AllArgsConstructor
public class HostDetails {

    private Long id;
    private String deviceName;
    private String ipAddress;
    private String status;
    private Double cpuUsage;
    private Double memoryUsage;
    private String loginTime;
    private String cpuModel;
    private String osVersion;

    @Override
    public String toString() {
        return "HostDetails{" +
                "deviceName='" + deviceName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", status='" + status + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", loginTime=" + loginTime +
                ", cpuModel=" + cpuModel +
                ", osVersion=" + osVersion +
                '}';
    }

}
