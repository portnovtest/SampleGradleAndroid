package com.sample.framework;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;

public final class Driver {
    private Driver(){
    }

    private static ConcurrentHashMap<String, WebDriver> driverThreadMap = new ConcurrentHashMap<>();
    public static String getThreadName(){
        return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    }
    public static void add(WebDriver driver){
        String threadName = getThreadName();
        driverThreadMap.put(threadName, driver);
    }
    public static WebDriver current(){
        String threadName = getThreadName();
        return driverThreadMap.get(threadName);
    }
}