package com.test.controller;

import com.test.domain.HostStatus;
import com.test.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping
    public String monitorPage() {
        return "monitor"; // 这里假设返回一个 Thymeleaf 模板
    }

    // 接收主机状态更新
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestBody HostStatus hostStatus) {
        monitorService.updateHostStatus(hostStatus);
        return "Status updated successfully!";
    }

    // 查询主机状态
    @GetMapping("/status/{hostname}")
    public HostStatus getHostStatus(@PathVariable String hostname) {
        return monitorService.getHostStatus(hostname);
    }
}
