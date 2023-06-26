package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitsSauceDemo {
    public static final int MEDIUM_DURATION_OF_WAITING = 10;

    private ExplicitWaitsSauceDemo() {
    }

    public static void waitPage(WebDriver driver, String pageTitle) {
        (new WebDriverWait(driver, Duration.ofSeconds(MEDIUM_DURATION_OF_WAITING))).
                until(ExpectedConditions.
                        textToBePresentInElement(driver.findElement(By.className("title")), pageTitle));
    }

    public static void waitPageByElementId(WebDriver driver, String elementId) {
        (new WebDriverWait(driver, Duration.ofSeconds(MEDIUM_DURATION_OF_WAITING))).
                until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
    }

    public static void waitButton(WebDriver driver, String xPath) {
        (new WebDriverWait(driver, Duration.ofSeconds(MEDIUM_DURATION_OF_WAITING))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }
}
