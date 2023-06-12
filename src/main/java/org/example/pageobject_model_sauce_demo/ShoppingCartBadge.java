package org.example.pageobject_model_sauce_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ShoppingCartBadge {
    protected WebDriver driver;

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
        return shoppingCartBadgeCount;
    }

    public ShoppingCart openShoppingCart() {
        driver.findElement(By.cssSelector("#shopping_cart_container > a")).click();
        ExplicitWaits.waitPage(driver, "Your Cart");
        return new ShoppingCart(driver);
    }

}
