package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.drivers.DriverContainer.LOGGER;

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

        public void checkCurrentPage(WebDriver driver, String className, String textValue, String pageName) {
            try {
                if (findElementTextByClassName(driver, className).equals(textValue)) {
                    LOGGER.info(String.format("%s page is displayed", pageName));
            } else {
                throw new NoSuchElementException(String.format(NOT_THE_REQUIRED_PAGE, pageName));
            }
        } catch (NoSuchElementException e) {
                LOGGER.error(String.format(NOT_THE_REQUIRED_PAGE, pageName) + "current page is: " + driver.getCurrentUrl());
            throw new IllegalStateException();
        }
    }
}
