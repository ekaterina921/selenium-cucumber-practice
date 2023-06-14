package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Log4j
public class DriverCreator {

    private DriverCreator() {
    }

    public static WebDriver create(String browser) {
            switch (browser) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                }
            }
    }
}
