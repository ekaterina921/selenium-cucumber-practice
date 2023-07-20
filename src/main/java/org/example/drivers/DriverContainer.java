package org.example.drivers;

import lombok.extern.log4j.Log4j;
import org.example.pageobject_model_sauce_demo.SauceDemoPages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


    public class DriverContainer {
        static ThreadLocal<WebDriver> local = new ThreadLocal<>();
        public static final Logger LOGGER = LoggerFactory.getLogger(DriverContainer.class);
    public DriverContainer() {
    }

    public static WebDriver getDriver() {
            if (local.get() == null) {

                LOGGER.debug("New driver created.");
                local.set(DriverCreator.create(System.getProperty("browser")));
            }
            return local.get();
        }

        public static void removeDriver() {
            if (local.get() != null) {
                LOGGER.debug("Driver removed. Test ended.");
                local.remove();
            }
        }
    }
