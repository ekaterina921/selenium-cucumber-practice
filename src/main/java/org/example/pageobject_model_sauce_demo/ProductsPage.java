package org.example.pageobject_model_sauce_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {

    public ProductsPage(WebDriver driver) {
        checkCurrentPage(driver, "title", "Products");
    }

    public void openInventoryItem(WebDriver driver, String itemName) {
        String xPathToItem = "//*[text()='" + itemName + "']";
        driver.findElement(By.xpath(xPathToItem)).click();
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
    }

}
