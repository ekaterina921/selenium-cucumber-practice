package test;

import org.example.drivers.DriverContainer;
import org.example.models.User;
import org.example.pageobject_model_sauce_demo.*;
import org.example.service.UserCreator;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SauceDemoTests extends BaseTestConfig implements ConstantsSauceDemo {

    @Test
    public void testCheckoutHappyPath() {
        User testUser = UserCreator.withCredentialsFromProperty("sauce");
        driver.navigate().to(SIGN_IN_PAGE_URL);
        SignInPage signInPage = new SignInPage(driver);
        ProductsPage productsPage = signInPage.signIn(testUser);
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        CheckoutPages checkoutPages = shoppingCart.clickCheckoutButton();
        checkoutPages.fillAndSubmitCheckoutForm("Ann", "Johnson", "194378");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(),
                1, "The number of Products is displayed incorrectly on Checkout page! Should be 1.");
        softAssert.assertEquals(checkoutPages.findElementTextById(driver, "item_4_title_link"),
                SAUCE_LABS_BACKPACK,
                String.format("Product title %s is displayed incorrectly on the Checkout page!", SAUCE_LABS_BACKPACK));
        softAssert.assertEquals(Integer.parseInt
                        (checkoutPages.findElementTextByCssSelector(driver, FINDING_QUANTITY_OF_SINGLE_PRODUCT_IN_CART)),
                1, "The quantity of the product is displayed incorrectly on Checkout page! Should be 1.");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, FINDING_PRICE_OF_SINGLE_PRODUCT_IN_CART),
                PRICE_SAUCE_LABS_BACKPACK,
                String.format("Expected price %s is displayed incorrectly on Checkout page!", PRICE_SAUCE_LABS_BACKPACK));
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(1)"),
                "Payment Information",
                "Payment Information summary info label is not displayed on Checkout page!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(2)"),
                "SauceCard #31337",
                "Payment Information summary value label: 'SauceCard #31337' is not displayed on Checkout page!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(3)"),
                "Shipping Information",
                "Shipping Information summary info label is not displayed on Checkout page!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(4)"),
                "Free Pony Express Delivery!",
                "Shipping Information summary value label: 'Free Pony Express Delivery!' is not displayed on Checkout page!");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "div.summary_info > div:nth-child(5)"),
                "Price Total",
                "Price Total summary info label is not displayed on Checkout page!");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "summary_subtotal_label"),
                "Item total: " + PRICE_SAUCE_LABS_BACKPACK,
                String.format("The price of the product %s is displayed incorrectly on Checkout page! Should be Item total: %s.",
                        SAUCE_LABS_BACKPACK, PRICE_SAUCE_LABS_BACKPACK));
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "summary_tax_label"),
                "Tax: $2.40",
                "The Tax is displayed incorrectly on Checkout page! Should be 'Tax: $2.40'");
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, ".summary_info_label.summary_total_label"),
                "Total: $32.39",
                "The total price is displayed incorrectly on Checkout page! Should be 'Total: $32.39'");

        checkoutPages.finishCheckout();
        softAssert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "#back-to-products"),
                "Back Home",
                "Back Home button text is not displayed on Checkout:Complete! page!");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-text"),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Complete-text is not displayed on Checkout:Complete! page!");
        softAssert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-header"),
                "Thank you for your order!",
                "Complete header is not displayed on Checkout:Complete! page!");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void testAddingToAndRemovingProductFromCartOnInventoryDetailsPage(){
        User testUser = UserCreator.withCredentialsFromProperty("sauce");
        driver.navigate().to(SIGN_IN_PAGE_URL);
        SignInPage signInPage = new SignInPage(driver);
        ProductsPage productsPage = signInPage.signIn(testUser);
        productsPage.openInventoryItem(driver, SAUCE_LABS_BACKPACK);
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 1,
                "Shopping Cart Badge is displayed incorrectly after adding 1 product to cart on Inventory Item page. Should be 1.");
        productsPage.removeFromCart(driver, REMOVING_FROM_CART_SAUCE_LABS_BACKPACK);
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0,
                "Shopping Cart Badge is displayed incorrectly after removing product from cart on Inventory Item page. Should be 0.");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testAddingToAndRemovingSeveralProductsToCart(){
        User testUser = UserCreator.withCredentialsFromProperty("sauce");
        driver.navigate().to(SIGN_IN_PAGE_URL);
        SignInPage signInPage = new SignInPage(driver);
        ProductsPage productsPage = signInPage.signIn(testUser);
        productsPage.addToCart(driver, ADDING_TO_CART_SAUCE_LABS_BACKPACK);
        productsPage.addToCart(driver, "add-to-cart-sauce-labs-onesie");
        ShoppingCartBadge shoppingCartBadge = new ShoppingCartBadge(driver);
        ShoppingCart shoppingCart = shoppingCartBadge.openShoppingCart();
        shoppingCart.removeFromCart(driver, REMOVING_FROM_CART_SAUCE_LABS_BACKPACK);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 1,
                "The number of products in the cart is displayed incorrectly. Should be 1.");
        softAssert.assertEquals(shoppingCart.findElementTextById(driver, "item_2_title_link"), SAUCE_LABS_ONESIE);
        softAssert.assertEquals(Integer.parseInt(shoppingCart.findElementTextByCssSelector(driver, FINDING_QUANTITY_OF_SINGLE_PRODUCT_IN_CART)),
                1,
                String.format("The quantity of the product %s in the cart is displayed incorrectly. Should be 1.", SAUCE_LABS_ONESIE));
        softAssert.assertEquals(shoppingCart.findElementTextByClassName(driver, FINDING_PRICE_OF_SINGLE_PRODUCT_IN_CART),
                PRICE_SAUCE_LABS_ONESIE,
                String.format("The price of the product %s in the cart is displayed incorrectly. Should be %s",
                        SAUCE_LABS_ONESIE, PRICE_SAUCE_LABS_ONESIE));
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 1,
                "Shopping Cart Badge is displayed incorrectly. Should be 1.");
        shoppingCart.removeFromCart(driver, "remove-sauce-labs-onesie");
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 0,
                "The number of products in the cart is incorrect. Should be 0.");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0,
                "Shopping Cart Badge is displayed incorrectly. Should be 1.");
        softAssert.assertAll();
    }

}

