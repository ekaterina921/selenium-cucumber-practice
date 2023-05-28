package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject_model.*;


public class SauceDemoTests {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        SignInPage signInPage = new SignInPage(driver);
        signInPage.loginValidUser("standard_user", "secret_sauce");
    }

    @Test
    public void testCheckoutHappyPath() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-backpack");
        String expectedPrice = "$29.99";
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        CheckoutPages checkoutPages = shoppingCart.clickCheckoutButton();
        checkoutPages.fillAndSubmitCheckoutForm("Ann", "Johnson", "194378");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1);
        softAssert.assertEquals(driver.findElement
                (By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        softAssert.assertEquals(Integer.parseInt
                (checkoutPages.findElementTextByCssSelector(".cart_quantity")), 1);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName("inventory_item_price"),
                expectedPrice);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName("summary_subtotal_label"),
                "Item total: " + expectedPrice);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName("summary_tax_label"),
                "Tax: $2.40");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(".summary_info_label.summary_total_label"),
                "Total: $32.39");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector("div.summary_info > div:nth-child(1)"),
                "Payment Information");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector("div.summary_info > div:nth-child(2)"),
                "SauceCard #31337");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector("div.summary_info > div:nth-child(3)"),
                "Shipping Information");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector("div.summary_info > div:nth-child(4)"),
                "Free Pony Express Delivery!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector("div.summary_info > div:nth-child(5)"),
                "Price Total");

        checkoutPages.finishCheckout();
        softAssert.assertEquals(driver.findElement(By.cssSelector("#back-to-products")).getText(), "Back Home");
        softAssert.assertEquals(driver.findElement(By.className("complete-text")).getText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        softAssert.assertEquals(driver.findElement(By.className("complete-header")).getText(),
                "Thank you for your order!");
        softAssert.assertTrue(driver.findElement(By.id("checkout_complete_container"))
                .findElement(By.className("pony_express"))
                .isDisplayed());
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void testAddingToAndRemovingProductFromCartOnInventoryDetailsPage() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openInventoryItem("Sauce Labs Backpack");
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-backpack");
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        Assert.assertEquals(shoppingCartBadge.getShoppingCartBadge(), 1);
        productsPage.removeFromCart(driver, "remove-sauce-labs-backpack");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadge(), 0);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testAddingToAndRemovingSeveralProductsToCart() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-backpack");
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-onesie");
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        shoppingCart.removeFromCart(driver, "remove-sauce-labs-backpack");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.countProductsInCart(), 1);
        softAssert.assertEquals(driver.findElement(By.id("item_2_title_link")).getText(), "Sauce Labs Onesie");
        softAssert.assertEquals(Integer.parseInt(driver.findElement
                (By.cssSelector(".cart_quantity")).getText()), 1);
        softAssert.assertEquals(driver.findElement
                        (By.className("inventory_item_price")).getText(),
                "$7.99");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadge(), 1);
        shoppingCart.removeFromCart(driver, "remove-sauce-labs-onesie");
        softAssert.assertEquals(shoppingCart.countProductsInCart(), 0);
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadge(), 0);
        softAssert.assertAll();
    }

    @AfterTest
    public void quitBrowser() {
        driver.quit();
    }
}
