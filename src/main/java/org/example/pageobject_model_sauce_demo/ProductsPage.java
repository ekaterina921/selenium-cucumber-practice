package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.utils.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.drivers.DriverContainer.LOGGER;

public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {
    public static final String INVENTORY_ITEM_PAGE = "Inventory item";

    public ProductsPage(WebDriver driver) {
        checkCurrentPage(driver, "title", "Products", "Products");
    }

    public void openInventoryItem(WebDriver driver, String itemName) {
        String xPathToItem = "//*[text()='" + itemName + "']";
        driver.findElement(By.xpath(xPathToItem)).click();
//        log.debug(String.format("%s page opening is triggered.", INVENTORY_ITEM_PAGE));
        LOGGER.debug(String.format("%s page opening is triggered.", INVENTORY_ITEM_PAGE));
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
//        log.info("Inventory item page is opened.");
        LOGGER.info("Inventory item page is opened.");
    }

}
