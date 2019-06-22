package com.sample.framework.utils;

import com.sample.framework.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SystemUtils {
    public SystemUtils() {
    }

    private static void runCommand(String[] cmdArray){
        try {
            Runtime.getRuntime().exec(cmdArray).waitFor(3, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getADBPath(){
        return System.getenv("ANDROID_HOME") + File.separator + "platform-tools" + File.separator + "adb";
    }
    public static void setSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.HHmmss");
        Date date = new Date();

        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "date", "-s",
                    format.format(date)
            };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "date", "-s",
                    format.format(date)
            };
        }
        runCommand(cmdArray);
    }
    public static void resetAppData() {
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "pm", "clear",
                    Configuration.get("appPackage")
            };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "pm", "clear",
                    Configuration.get("appPackage")
            };
        }
        runCommand(cmdArray);
    }
    public static void uninstallApp(String appId){
        String deviceId = Configuration.get("udid");
        uninstallApp(deviceId, appId);
    }
    public static void uninstallApp(String udid, String appId){
        String[] cmdArray;
        if (StringUtils.isNotBlank(udid)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    udid, "uninstall",
                    appId
            };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "uninstall",
                    appId
            };
        }
        runCommand(cmdArray);
    }
    public static void openDeepLink(String url){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "am", "start", url };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "am", "start", url };
        }
        runCommand(cmdArray);
    }
}
