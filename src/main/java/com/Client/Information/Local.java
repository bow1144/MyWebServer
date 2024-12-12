package com.Client.Information;

import java.io.IOException;
import java.net.InetAddress;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Local {

    // 获取本地系统的设备名称（设备名称一般可以自定义，这里以"Client_Device_01"为示例）
    public String getDeviceName() {
        // 获取本地机器的 InetAddress
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostName();  // 返回本机的IP地址
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown Device Name";  // 如果获取失败，返回"Unknown IP"
        }
    }

    // 获取本地IP地址
    public String getLocalIPAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();  // 返回本机的IP地址
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown IP";  // 如果获取失败，返回"Unknown IP"
        }
    }

    // 获取设备状态（在线状态示例）
    public String getDeviceStatus() {
        return "Online";  // 默认在线
    }

    // 获取CPU使用率（百分比）
    public double getCPUUsage() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 计算 CPU 使用率（假设在 Linux 或其他 Unix-like 系统上）
        double cpuLoad = osBean.getSystemCpuLoad();        // 获取系统的CPU负载
        return cpuLoad * 100;  // 转换为百分比
    }

    // 获取内存使用率（百分比）
    public double getMemoryUsage() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalMemory = osBean.getTotalPhysicalMemorySize();  // 总内存
        long freeMemory = osBean.getFreePhysicalMemorySize();   // 剩余内存
        return (1 - (freeMemory / (double) totalMemory)) * 100;  // 计算内存使用率
    }

    // 获取当前系统时间的时间戳（单位：毫秒）
    public long getLoginTime() {
        return System.currentTimeMillis();  // 获取当前时间的时间戳
    }

    // 获取CPU型号
    public String getCpuModel() {
        String cpuModel = "Unknown";
        try {
            Process process = Runtime.getRuntime().exec("wmic cpu get caption");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            cpuModel = reader.readLine();  // 获取输出中的 CPU 型号
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuModel;
    }

    // 获取操作系统型号
    public String getOSVersion() {
        String osName = System.getProperty("os.name");  // 操作系统名称
        String osVersion = System.getProperty("os.version");  // 操作系统版本
        String osArch = System.getProperty("os.arch");  // 操作系统架构
        return osName+" "+osVersion;
    }
}
