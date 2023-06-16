package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j
public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {
    public static final String INVENTORY_ITEM_PAGE = "Inventory item";

    public ProductsPage(WebDriver driver) {
        checkCurrentPage(driver, "title", "Products");
    }

    public void openInventoryItem(WebDriver driver, String itemName) {
        String xPathToItem = "//*[text()='" + itemName + "']";
        driver.findElement(By.xpath(xPathToItem)).click();
        log.debug(String.format("%s page opening is triggered.", INVENTORY_ITEM_PAGE));
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
        log.info("Inventory item page is opened.");
    }

}
