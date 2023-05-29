package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaits {
    public static final int DURATIONOFWAITING = 10;
    public static void waitPage(WebDriver driver, String pageTitle) {
        (new WebDriverWait(driver, Duration.ofSeconds(DURATIONOFWAITING))).
                until(ExpectedConditions.
                        textToBePresentInElement(driver.findElement(By.className("title")), pageTitle));
    }

    public static void waitInventoryItemPage(WebDriver driver) {
        (new WebDriverWait(driver, Duration.ofSeconds(DURATIONOFWAITING))).
                until(ExpectedConditions.presenceOfElementLocated(By.id("back-to-products")));
    }

    public static void waitRemoveButton(WebDriver driver) {
        (new WebDriverWait(driver, Duration.ofSeconds(DURATIONOFWAITING))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Remove']")));
    }

    public static void waitAddToCartButton(WebDriver driver) {
        (new WebDriverWait(driver, Duration.ofSeconds(DURATIONOFWAITING))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Add to cart']")));
    }
}
