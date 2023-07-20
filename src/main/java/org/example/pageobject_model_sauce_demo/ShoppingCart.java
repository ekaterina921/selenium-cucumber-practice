package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j2;
import org.example.utils.ExplicitWaits;
import org.example.utils.ExtendedListener;
import org.example.utils.LoggingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;


@Log4j2
@Listeners({ExtendedListener.class})
public class ShoppingCart extends SauceDemoPages implements InventoryItemsActions {

    protected WebDriver driver;
    public static final String YOUR_INFORMATION_PAGE = "Checkout: Your Information";

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPages clickCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        LoggingUtils.logPlain(String.format("%s page opening is triggered.", YOUR_INFORMATION_PAGE));
        ExplicitWaits.waitPage(driver, YOUR_INFORMATION_PAGE);
        return new CheckoutPages(driver);
    }

    @Override
    public void removeFromCart(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
       LoggingUtils.logPlain("A product is removed from the cart.");
    }

    public int getCountProductsInCart() {
        LoggingUtils.logPlain("Counting the number of products in the cart.");
        return driver.findElements
                (By.className("cart_item")).size();
    }

}
