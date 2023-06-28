package org.example.pageobject_model_sauce_demo;

import org.example.utils.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public interface InventoryItemsActions {
    default void addToCart(WebDriver driver, String name) {
        driver.findElement(By.name(name)).click();
        ExplicitWaits.waitButton(driver,"//*[text()='Remove']");
    }

    default void removeFromCart(WebDriver driver, String name) {
        driver.findElement(By.name(name)).click();
        ExplicitWaits.waitButton(driver, "//*[text()='Add to cart']");
    }


}
