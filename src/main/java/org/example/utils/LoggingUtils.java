package org.example.utils;


import com.epam.reportportal.utils.files.Utils;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
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

    public static void logPlain(String message) {
        try {
            File file = File.createTempFile("rp-test", ".txt");
            Utils.getFile(new File("plain.txt")).copyTo(Files.asByteSink(file));
            LoggingUtils.log(file, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
