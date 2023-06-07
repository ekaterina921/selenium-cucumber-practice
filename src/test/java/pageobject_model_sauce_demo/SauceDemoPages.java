package pageobject_model_sauce_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class SauceDemoPages {

    public SauceDemoPages() {
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
                    throw new NoSuchElementException(String.format("This is not %s page", pageTitle));
                }
            }
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(String.format("This is not %s Page,", pageTitle) +
                    "current page is: " + driver.getCurrentUrl());
        }
    }
}
