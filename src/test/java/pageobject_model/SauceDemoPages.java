package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoPages {

    protected WebDriver driver;

    public SauceDemoPages() {
    }

    public String findElementTextByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)).getText();
    }

    public String findElementTextByClassName (String className) {
        return driver.findElement
                (By.className(className)).getText();
    }
}
