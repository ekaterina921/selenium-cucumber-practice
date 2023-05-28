package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoPages {

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
}
