package org.example.drivers;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;


@Log4j
    public class DriverContainer {
        static ThreadLocal<WebDriver> local = new ThreadLocal<>();

    private DriverContainer() {
    }

    public static WebDriver getDriver() {
            if (local.get() == null) {
                log.debug("New driver created.");
                local.set(DriverCreator.create(System.getProperty("browser")));
            }
            return local.get();
        }

        public static void removeDriver() {
            if (local.get() != null) {
                log.debug("Driver removed. Test ended.");
                local.remove();
            }
        }
    }
