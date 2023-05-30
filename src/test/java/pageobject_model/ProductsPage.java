package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends SauceDemoPages implements InventoryItemsActions {
    protected WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        try {
            if (!findElementTextByClassName(driver, "title").equals("Products")) {
                throw new NoSuchElementException("This is not Products page");
            }
        } catch (NoSuchElementException e) {
            {
                throw new IllegalStateException("This is not Products page," +
                        " current page is: " + driver.getCurrentUrl());
            }
        }
    }

    public void openInventoryItem(String itemName) {
        driver.findElement(By.linkText(itemName)).click();
        ExplicitWaits.waitPageByElementId(driver, "back-to-products");
    }

}
