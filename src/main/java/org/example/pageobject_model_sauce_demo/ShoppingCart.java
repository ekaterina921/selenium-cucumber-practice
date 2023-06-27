package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import org.example.utils.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@Log4j
public class ShoppingCart extends SauceDemoPages implements InventoryItemsActions {

    protected WebDriver driver;
    public static final String YOUR_INFORMATION_PAGE = "Checkout: Your Information";

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPages clickCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        log.debug(String.format("%s page opening is triggered.", YOUR_INFORMATION_PAGE));
        ExplicitWaits.waitPage(driver, YOUR_INFORMATION_PAGE);
        return new CheckoutPages(driver);
    }

    @Override
    public void removeFromCart(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        log.debug("A product is removed from the cart.");
    }

    public int getCountProductsInCart() {
        log.debug("Counting the number of products in the cart.");
        return driver.findElements
                (By.className("cart_item")).size();
    }

}
