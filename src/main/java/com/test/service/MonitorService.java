package com.test.service;

import com.test.domain.HostStatus;
import com.test.mapper.HostStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private HostStatusMapper hostStatusMapper;

    // TODO:已经设计好HostStatue类，接下来读取数据库
    public List<HostStatus> getAllStatus() {
        return hostStatusMapper.getAllStatus();
    }

    // 保存主机状态
    public void updateHostStatus(HostStatus hostStatus) {

    }

    // 根据主机名查询主机状态
    public HostStatus getHostStatus(String hostname) {
        return hostStatusMapper.findByHostname(hostname);  // 从数据库中查询
    }
}
