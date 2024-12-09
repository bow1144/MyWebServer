package com.test.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HostStatus {

    @Id
    private String username;
    private String hostname;  // 主机名
    private String status;    // 状态，如 "系统启动", "IE关闭"
    private String timestamp; // 时间戳

    // Getters and Setters
}
