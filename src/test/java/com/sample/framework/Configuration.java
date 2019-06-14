package com.sample.framework;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public final class Configuration {

    private Configuration() {

    }
    private static Properties properties;
    public static void load() throws Exception {
        Configuration config = new Configuration();
        properties = new Properties();
        InputStream is = new FileInputStream(new File("config.properties"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        try {
            properties.load(reader);
        } finally {
            is.close();
            reader.close();
        }
    }
    public static String get(String option) throws Exception {
        if (properties == null){
            load();
        }
        String value = properties.getProperty(option);
        if (value == null){
            return "";
        }
        return value;
    }
    public static void print(){
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }
}
