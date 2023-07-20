package org.example.pageobject_model_sauce_demo;

import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import lombok.extern.log4j.Log4j2;
import org.example.utils.ExplicitWaits;
import org.example.utils.ExtendedListener;
import org.example.utils.LoggingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static org.example.drivers.DriverContainer.LOGGER;

@Log4j2
@Listeners({ExtendedListener.class})
public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {
    public static final String INVENTORY_ITEM_PAGE = "Inventory item";
    private static final String JSON_FILE_PATH = "file.json";

    public ProductsPage(WebDriver driver) {
        checkCurrentPage(driver, "title", "Products", "Products");
    }

    public void openInventoryItem(WebDriver driver, String itemName) throws IOException {
        String xPathToItem = "//*[text()='" + itemName + "']";
        driver.findElement(By.xpath(xPathToItem)).click();
        try {
        LOGGER.info(
                "RP_MESSAGE#BASE64#{}#{}",
                BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read()),
                "I'm logging content via BASE64"
        ); } catch(IOException e) {
            e.printStackTrace();
            }
        LoggingUtils.logPlain(String.format("%s page opening is triggered.", INVENTORY_ITEM_PAGE));
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
        LoggingUtils.logPlain("Inventory item page is opened.");
    }

}
