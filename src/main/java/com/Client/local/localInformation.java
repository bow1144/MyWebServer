package com.Client.local;

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
}
