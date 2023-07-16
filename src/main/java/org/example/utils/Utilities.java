package org.example.utils;

import org.apache.commons.io.FileUtils;

import org.example.drivers.DriverContainer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Utilities {
//    protected void saveScreenshot(){
//        File screenCapture = ((TakesScreenshot) DriverContainer
//                .getDriver())
//                .getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(screenCapture, new File(
//                    ".//target/screenshots/"
//                            + getCurrentTimeAsString() +
//                            ".png"));
//        } catch (IOException e) {
//            LOGGER.error("Failed to save screenshot: " + e.getLocalizedMessage());
//        }
//    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}
