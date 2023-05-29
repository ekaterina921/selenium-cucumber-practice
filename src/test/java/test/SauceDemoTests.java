package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        signInPage.signIn("standard_user", "secret_sauce");
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
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 1);
        softAssert.assertEquals(checkoutPages.findElementTextById(driver, "item_4_title_link"), "Sauce Labs Backpack");
        softAssert.assertEquals(Integer.parseInt
                (checkoutPages.findElementTextByCssSelector(driver, ".cart_quantity")), 1);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "inventory_item_price"),
                expectedPrice);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "summary_subtotal_label"),
                "Item total: " + expectedPrice);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "summary_tax_label"),
                "Tax: $2.40");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, ".summary_info_label.summary_total_label"),
                "Total: $32.39");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(1)"),
                "Payment Information");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(2)"),
                "SauceCard #31337");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(3)"),
                "Shipping Information");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(4)"),
                "Free Pony Express Delivery!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(5)"),
                "Price Total");

        checkoutPages.finishCheckout();
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "#back-to-products"), "Back Home");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-text"),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-header"),
                "Thank you for your order!");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void testAddingToAndRemovingProductFromCartOnInventoryDetailsPage() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openInventoryItem("Sauce Labs Backpack");
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-backpack");
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 1);
        productsPage.removeFromCart(driver, "remove-sauce-labs-backpack");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0);
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
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 1);
        softAssert.assertEquals(shoppingCart.findElementTextById(driver, "item_2_title_link"), "Sauce Labs Onesie");
        softAssert.assertEquals(Integer.parseInt(shoppingCart.findElementTextByCssSelector(driver, ".cart_quantity")), 1);
        softAssert.assertEquals(shoppingCart.findElementTextByClassName(driver, "inventory_item_price"),
                "$7.99");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 1);
        shoppingCart.removeFromCart(driver, "remove-sauce-labs-onesie");
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 0);
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0);
        softAssert.assertAll();
    }

    @AfterTest
    public void quitBrowser() {
        driver.quit();
    }
}
