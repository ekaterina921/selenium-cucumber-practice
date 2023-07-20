package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j2;
import org.example.utils.ExplicitWaits;
import org.example.utils.ExtendedListener;
import org.example.utils.LoggingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({ExtendedListener.class})
public class ShoppingCartBadge {
    protected WebDriver driver;
    public static final String YOUR_CART_PAGE_TITLE = "Your Cart";

    public ShoppingCartBadge(WebDriver driver) {
        this.driver = driver;
    }

    public int getShoppingCartBadgeCount() {
        int shoppingCartBadgeCount;
        try {
            shoppingCartBadgeCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
        } catch (NoSuchElementException e) {
            shoppingCartBadgeCount = 0;
        }
        LoggingUtils.logPlain("ShoppingCartBadgeCount is read.");
        return shoppingCartBadgeCount;
    }

    public ShoppingCart openShoppingCart() {
        driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();
        ExplicitWaits.waitPage(driver, YOUR_CART_PAGE_TITLE);
        return new ShoppingCart(driver);
    }

}
