package com.test.controller;

import com.test.domain.HostDetails;
import com.test.domain.HostStatus;
import com.test.service.MonitorService;
import com.test.timeService.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping()
    public String monitorPage(Model model) {
        List<HostDetails> details = monitorService.getAllDetails();
        System.out.println("Details datas:" + details);
        model.addAttribute("details", details);
        return "monitor"; // 这里假设返回一个 Thymeleaf 模板
    }

    // 接收主机状态更新
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateDetails(@RequestBody HostDetails hostDetails) {
        // 确保 loginTime 是 LocalDateTime 类型
        String loginTime = LocalDateTime.now().toString();
        System.out.println("loginTime: " + loginTime);
        hostDetails.setLoginTime(loginTime);  // 设置转换后的时间

        System.out.println("Received host details: " + hostDetails);
        monitorService.saveHostDetails(hostDetails);
        return ResponseEntity.ok("Status updated successfully!");    }

    // 查询主机状态
    @GetMapping("/status/{hostname}")
    public HostStatus getHostStatus(@PathVariable String hostname) {
        return monitorService.getHostStatus(hostname);
    }
}
