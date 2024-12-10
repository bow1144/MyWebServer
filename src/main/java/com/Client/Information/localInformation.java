package com.Client.Information;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class localInformation {

    public String getOsInf() {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        System.out.println("OS Name: " + osName);
        System.out.println("OS Version: " + osVersion);
        System.out.println("OS Architecture: " + osArch);

        return osName + osVersion;
    }

    public double getCPUInf () {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        double cpuLoad = osBean.getSystemCpuLoad();
        int availableProcessors = osBean.getAvailableProcessors();

        System.out.println("CPU Load: " + cpuLoad);
        System.out.println("Available Processors: " + availableProcessors);

        return cpuLoad;
    }

    public long getMemoryInf () {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();

        System.out.println("Total Memory: " + totalMemory);
        System.out.println("Free Memory: " + freeMemory);

        return freeMemory;
    }

}
