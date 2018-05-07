package com.wtu.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertyUtil {

    private static final String APP_PROPERTIES = "app.properties";

    private static Logger logger = Logger.getLogger(PropertyUtil.class);

    private static Properties properties = null;

    static {
        InputStream in = null;

        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(APP_PROPERTIES);
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}