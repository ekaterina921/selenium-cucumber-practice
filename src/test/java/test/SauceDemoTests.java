package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject_model.*;


public class SauceDemoTests implements Constants {
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
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        CheckoutPages checkoutPages = shoppingCart.clickCheckoutButton();
        checkoutPages.fillAndSubmitCheckoutForm("Ann", "Johnson", "194378");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 1);
        softAssert.assertEquals(checkoutPages.findElementTextById(driver, "item_4_title_link"), SAUCE_LABS_BACKPACK);
        softAssert.assertEquals(Integer.parseInt
                (checkoutPages.findElementTextByCssSelector(driver, FINDING_QUANTITY_OF_SINGLE_PRODUCT_IN_CART)), 1);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, FINDING_PRICE_OF_SINGLE_PRODUCT_IN_CART),
                PRICE_SAUCE_LABS_BACKPACK);
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "summary_subtotal_label"),
                "Item total: " + PRICE_SAUCE_LABS_BACKPACK);
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
        driver.navigate().to(PRODUCTS_PAGE_URL);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openInventoryItem(SAUCE_LABS_BACKPACK);
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 1);
        productsPage.removeFromCart(driver, REMOVING_FROM_CART_SAUCE_LABS_BACKPACK);
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testAddingToAndRemovingSeveralProductsToCart() {
        driver.navigate().to(PRODUCTS_PAGE_URL);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-onesie");
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        shoppingCart.removeFromCart(driver, REMOVING_FROM_CART_SAUCE_LABS_BACKPACK);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 1);
        softAssert.assertEquals(shoppingCart.findElementTextById(driver, "item_2_title_link"), "Sauce Labs Onesie");
        softAssert.assertEquals(Integer.parseInt(shoppingCart.findElementTextByCssSelector(driver, FINDING_QUANTITY_OF_SINGLE_PRODUCT_IN_CART)), 1);
        softAssert.assertEquals(shoppingCart.findElementTextByClassName(driver, FINDING_PRICE_OF_SINGLE_PRODUCT_IN_CART),
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
