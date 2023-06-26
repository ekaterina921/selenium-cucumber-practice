package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import org.example.utils.ExplicitWaitsSauceDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

@Log4j
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
        log.debug("ShoppingCartBadgeCount is read.");
        return shoppingCartBadgeCount;
    }

    public ShoppingCart openShoppingCart() {
        driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();
        ExplicitWaitsSauceDemo.waitPage(driver, YOUR_CART_PAGE_TITLE);
        log.info(String.format("%s page is opened.", YOUR_CART_PAGE_TITLE));
        return new ShoppingCart(driver);
    }

}
