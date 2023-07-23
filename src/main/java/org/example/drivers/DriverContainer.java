package org.example.drivers;

import lombok.extern.log4j.Log4j2;
import org.example.utils.ExtendedListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({ ExtendedListener.class})
    public class DriverContainer {
        static ThreadLocal<WebDriver> local = new ThreadLocal<>();
    public DriverContainer() {
    }

    public WebDriver getDriver() {
            if (local.get() == null) {
                log.debug("New driver created.");
                DriverCreator driverCreator = new DriverCreator();
                local.set(driverCreator.create(System.getProperty("browser")));
            }
            return local.get();
        }

        public void removeDriver() {
            if (local.get() != null) {
                log.debug("Driver removed. Test ended.");
                local.remove();
            }
        }
    }
