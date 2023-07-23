package org.example.utils;


import com.epam.reportportal.utils.files.Utils;
import com.google.common.io.Files;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Log4j2
public class LoggingUtils {

    private LoggingUtils() {
        //statics only
    }
    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

    public static void log(File file, String message) {
        log.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

        public static void logBase64(String base64, String message) {
            LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
        }

    public static void log(byte[] bytes, String message) {
        log.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(bytes), message);
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
