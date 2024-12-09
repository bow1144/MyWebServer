package com.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // 自动生成 Getters, Setters, equals, hashCode, toString 等
@NoArgsConstructor
@AllArgsConstructor
public class HostStatus {
    private String name;      // 与xml文件中的列名相同
    private String hostname;  // 主机名
    private String status;    // 状态，如 "系统启动", "IE关闭"
    private String timestamp; // 时间戳
}
