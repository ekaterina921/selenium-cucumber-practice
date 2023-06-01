package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ShoppingCart extends SauceDemoPages implements InventoryItemsActions {

    protected WebDriver driver;

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPages clickCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        ExplicitWaits.waitPage(driver, "Checkout: Your Information");
        return new CheckoutPages(driver);
    }

    @Override
    public void removeFromCart(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public int getCountProductsInCart() {
        return driver.findElements
                (By.className("cart_item")).size();
    }

}
