package com.Client.Information;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import org.json.JSONObject;

public class localInformation {

    public static void main(String[] args) {
        // 获取本地系统信息（这里只是一个示例，实际可以通过使用API获取更多信息）
        Local localInfo = new Local();

        // 获取本地系统信息
        String deviceName = localInfo.getDeviceName();
        String ipAddress = localInfo.getLocalIPAddress();
        String status = localInfo.getDeviceStatus();
        double cpuUsage = localInfo.getCPUUsage();
        double memoryUsage = localInfo.getMemoryUsage();
        String cpuModel = localInfo.getCpuModel();
        String osVersion = localInfo.getOSVersion();
        // 将设备信息发送到服务器
        sendDeviceInfo(deviceName, ipAddress, status, cpuUsage, memoryUsage, cpuModel, osVersion);
    }

    public static void sendDeviceInfo(String deviceName, String ipAddress, String status,
                                      double cpuUsage, double memoryUsage, String cpuModel, String osVersion) {
        try {
            // 构建JSON对象
            JSONObject json = new JSONObject();
            json.put("deviceName", deviceName);
            json.put("ipAddress", ipAddress);
            json.put("status", status);
            json.put("cpuUsage", cpuUsage);
            json.put("memoryUsage", memoryUsage);
            json.put("loginTime", System.currentTimeMillis());
            json.put("cpuModel", cpuModel);
            json.put("osVersion", osVersion);

            System.out.println(json.toString());

            // 发送POST请求
            URL url = new URL("http://localhost:8080/monitor/updateStatus");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取响应
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 模拟的CPU使用率
    private static double getCPUUsage() {
        return Math.random() * 100;  // 随机生成一个0-100之间的值，表示CPU使用率
    }

    // 模拟的内存使用率
    private static double getMemoryUsage() {
        return Math.random() * 100;  // 随机生成一个0-100之间的值，表示内存使用率
    }
}
