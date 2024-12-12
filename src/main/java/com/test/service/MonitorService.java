package com.test.service;

import com.test.domain.HostDetails;
import com.test.domain.HostStatus;
import com.test.mapper.HostDetailsMapper;
import com.test.mapper.HostStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private HostStatusMapper hostStatusMapper;

    @Autowired
    private HostDetailsMapper hostDetailsMapper;

    // TODO:已经设计好HostStatue类，接下来读取数据库
    public List<HostStatus> getAllStatus() {
        return hostStatusMapper.getAllStatus();
    }

    // 保存主机状态
    public void saveHostDetails(HostDetails hostDetails) {

        hostDetailsMapper.insertHostDetails(hostDetails);
    }
    // 根据主机名查询主机状态
    public HostStatus getHostStatus(String hostname) {
        return hostStatusMapper.findByHostname(hostname);  // 从数据库中查询
    }

    public List<HostDetails> getAllDetails() {
        System.out.println("Service Details data: " + hostDetailsMapper.getAllDetails());
        return hostDetailsMapper.getAllDetails();
    }

}
