package org.example.utils;


import java.io.File;
import java.util.Base64;

import static org.example.drivers.DriverContainer.LOGGER;

public class LoggingUtils {

    private LoggingUtils() {
        //statics only
    }

    public static void log(File file, String message) {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

    public static void log(byte[] bytes, String message) {
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(bytes), message);
    }

    public static void logBase64(String base64, String message) {
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
    }
}
