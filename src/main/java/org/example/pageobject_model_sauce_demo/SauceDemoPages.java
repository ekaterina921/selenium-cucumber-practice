package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

@Log4j
public abstract class SauceDemoPages {
    public static final String NOT_THE_REQUIRED_PAGE = "This is not %s page";

    protected SauceDemoPages() {
    }

    public String findElementTextByCssSelector(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)).getText();
    }

    public String findElementTextByClassName(WebDriver driver, String className) {
        return driver.findElement
                (By.className(className)).getText();
    }

    public String findElementTextById(WebDriver driver, String elementId) {
        return driver.findElement(By.id(elementId)).getText();
    }

    public void checkCurrentPage(WebDriver driver, String className, String pageTitle) {
        try {
            if (pageTitle.equalsIgnoreCase("Sign In")) {
                driver.findElement(By.className(className));
            } else {
                if (!findElementTextByClassName(driver, className).equals(pageTitle)) {
                    throw new NoSuchElementException(String.format(NOT_THE_REQUIRED_PAGE, pageTitle));
                }
            }
        } catch (NoSuchElementException e) {
            log.fatal(String.format(NOT_THE_REQUIRED_PAGE, pageTitle) + "current page is: " + driver.getCurrentUrl());
            throw new IllegalStateException();
        }
    }
}
