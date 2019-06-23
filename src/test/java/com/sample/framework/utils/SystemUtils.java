package com.sample.framework.utils;

import com.sample.framework.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public static void sendKeyEvent(String key){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "input", "keyevent", key };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "input", "keyevent", key };
        }
        runCommand(cmdArray);
    }
    public static void switchApp(){
        sendKeyEvent("KEYCODE_APP_SWITCH");
    }
    public static void killApp(){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "am", "kill", "--user", "all",
                    Configuration.get("appPackage")
            };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "am", "kill", "--user", "all",
                    Configuration.get("appPackage")
            };
        }
        runCommand(cmdArray);
    }
    public static void updateApp(String path){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "install", "-r", "-d",
                    path };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "install", "-r", "-d",
                    path };
        }
        runCommand(cmdArray);
    }
    public static void startApp(String packageName, String activityName){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "am", "start", "-n", packageName + "/" + activityName };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "am", "start", "-n", packageName + "/" + activityName };
        }
        runCommand(cmdArray);
    }
    public static void forceStop(String packageName) {
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "shell", "am", "force-stop", "--user", "all", packageName };
        } else {
            cmdArray = new String[]{
                    getADBPath(),
                    "shell", "am", "force-stop", "--user", "all", packageName };
        }
        runCommand(cmdArray);
    }
    public static void clearLogs(){
        String[] cmdArray;
        String deviceId = Configuration.get("udid");
        if (StringUtils.isNotBlank(deviceId)){
            cmdArray = new String[]{
                    getADBPath(), "-s",
                    deviceId, "logcat", "-c" };
        } else {
            cmdArray = new String[]{
                    getADBPath(), "logcat", "-c" };
        }
        runCommand(cmdArray);
    }
    public static void dumpErrorLog(File outputFile) throws Exception {
        String deviceId = Configuration.get("udid");

        ProcessBuilder builder = null;
        if (!StringUtils.isBlank(deviceId)){
            builder = new ProcessBuilder(getADBPath(), "-s", deviceId, "logcat", "-d", "*:E");
        } else {
            builder = new ProcessBuilder(getADBPath(), "logcat", "-d", "*:E");
        }
        builder.redirectOutput(outputFile);
        builder.redirectError(outputFile);
        Process p = builder.start();
        boolean status = p.waitFor(Configuration.timeout(), TimeUnit.SECONDS);
        Assert.assertTrue("Process didn't finish during specified timeout", status);
        Assert.assertEquals("Process ends with unexpected value", 0, p.exitValue());
    }
    public static List<String> getDisplayActivities() throws Exception {
        String deviceId = Configuration.get("udid");

        ProcessBuilder builder = null;
        if (!StringUtils.isBlank(deviceId)){
            builder = new ProcessBuilder(getADBPath(), "-s", deviceId, "logcat", "-d");
        } else {
            builder = new ProcessBuilder(getADBPath(), "logcat", "-d");
        }
        File outputFile = File.createTempFile("display", "log");
        outputFile.deleteOnExit();
        builder.redirectOutput(outputFile);
        builder.redirectError(outputFile);
        Process p = builder.start();
        boolean status = p.waitFor(Configuration.timeout(), TimeUnit.SECONDS);
        Assert.assertTrue("Process didn't finish during specified timeout", status);
        Assert.assertEquals("Process ends with unexpected value", 0, p.exitValue());
        List<String> lines = FileUtils.readLines(outputFile, "UTF-8");
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).matches("^(.*)ActivityManager(.*) Displayed(.*)$")){
                lines.remove(i);
                i--;
            }
        }
        return lines;
    }
    public static Process startProcessMetricsCommand(File outputFile) throws Exception {
        String deviceId = Configuration.get("udid");

        ProcessBuilder builder = null;
        if (!StringUtils.isBlank(deviceId)){
            builder = new ProcessBuilder(getADBPath(), "-s",
                    deviceId, "shell", "top", "-m", "10", "-d", "5 | grep " + Configuration.get("appPackage"));
        } else {
            builder = new ProcessBuilder(getADBPath(),
                    "shell", "top", "-m", "10", "-d", "5 | grep " + Configuration.get("appPackage"));
        }
        builder.redirectOutput(outputFile);
        builder.redirectError(outputFile);
        Process p = builder.start();
        return p;
    }
}
