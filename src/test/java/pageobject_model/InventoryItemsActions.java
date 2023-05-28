package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface InventoryItemsActions {
    default void addToCart(WebDriver driver, String name) {
        driver.findElement(By.name(name)).click();
        ExplicitWaits.waitRemoveButton(driver);
    }

    default void removeFromCart(WebDriver driver, String name) {
        driver.findElement(By.name(name)).click();
        ExplicitWaits.waitAddToCartButton(driver);
    }


}
