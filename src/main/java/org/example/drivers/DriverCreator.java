package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverCreator {

    private DriverCreator() {
    }

    protected static WebDriver create(String browser) {
            switch (browser) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                }
            }
    }
}
