package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {
    protected WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        checkCurrentPage(driver, "title", "Products");
    }

    public void openInventoryItem(String itemName) {
        driver.findElement(By.linkText(itemName)).click();
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
    }

}
