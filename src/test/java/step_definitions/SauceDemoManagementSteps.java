package step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.example.drivers.DriverContainer;
import org.example.models.SauceUser;
import org.example.pageobject_model_sauce_demo.*;
import org.example.service.SauceUserCreator;
import org.example.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static test.ConstantsSauceDemo.SIGN_IN_PAGE_URL;

@Log4j
@Listeners(TestListener.class)
public class SauceDemoManagementSteps{
    ProductsPage productsPage;
    ShoppingCart shoppingCart;
    ShoppingCartBadge shoppingCartBadge;
    CheckoutPages checkoutPages;
    WebDriver driver;

    @Before
    public void initTest(Scenario sc) {
        System.setProperty("environment", "qa");
        System.setProperty("browser", "chrome");
        log.debug("Start test." + sc.getName());
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void endTest() {
        new SauceUserCreator().removeUser();
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }

    @Given("User is logged in")
    public void user_is_logged_in() {
        SauceUser testUser = new SauceUserCreator().withCredentialsFromProperty();
        driver.navigate().to(SIGN_IN_PAGE_URL);
        productsPage = new SignInPage(driver)
                .signIn(testUser);
    }

    @When("User adds a {string} to cart")
    public void userAddsAToCart(String product) {
        productsPage.addToCart(driver, product);
    }

    @When("Navigate to cart")
    public void navigate_to_cart() {
        shoppingCartBadge = new ShoppingCartBadge(driver);
        shoppingCart = shoppingCartBadge.openShoppingCart();
    }
    @When("Checkout with the following identity")
    public void checkout_with_the_following_identity(DataTable dataTable) {
        List<List<String>> userData = dataTable.asLists(String.class);
        checkoutPages = shoppingCart.clickCheckoutButton();
        checkoutPages.fillAndSubmitCheckoutForm(userData.get(1).get(1), userData.get(2).get(1), userData.get(3).get(1));
        checkoutPages.finishCheckout();
    }
    @When("User removes a {string} from cart")
    public void user_removes_a_from_cart(String product) {
        shoppingCart.removeFromCart(driver, product);
    }

    @When("User removes a {string} from cart on Inventory Details page")
    public void user_removes_a_from_cart_from_inventory_details(String product) {
        productsPage.removeFromCart(driver, product);
    }

    @When("User opens Inventory Details page for {string}")
    public void user_opens_inventory_details_page_for(String product) {
        productsPage.openInventoryItem(driver, product);
    }

    @When("User adds several products to cart")
    public void user_adds_several_products_to_cart(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
                productsPage.addToCart(driver, columns.get(1));
        }
    }
    @When("User removes several products from cart")
    public void user_removes_several_products_from_cart(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            shoppingCart.removeFromCart(driver, columns.get(1));
        }
    }
    @Then("Shopping cart badge should be equal to {int}")
    public void shopping_cart_badge_should_be_equal_to(int shoppingCartBadgeCounter) {
        shoppingCartBadge = new ShoppingCartBadge(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), shoppingCartBadgeCounter,
                "Shopping Cart Badge is displayed incorrectly after removing product from cart on Inventory Item page.");
        softAssert.assertAll();
    }
    @Then("Checkout is successfully complete")
    public void checkout_is_successfully_complete() {
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(checkoutPages.findElementTextByCssSelector(driver, "#back-to-products"),
                "Back Home", "Back Home button text is not displayed on Checkout:Complete! page!");
        Assert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-text"),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Complete-text is not displayed on Checkout:Complete! page!");
        Assert.assertEquals(checkoutPages.findElementTextByClassName(driver, "complete-header"),
                "Thank you for your order!",
                "Complete header is not displayed on Checkout:Complete! page!");
        softAssert.assertAll();
    }

    @Then("Cart is empty")
    public void cart_is_empty() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCart.getCountProductsInCart(), 0,
                "The number of products in the cart is incorrect. Should be 0.");
        softAssert.assertEquals(shoppingCartBadge.getShoppingCartBadgeCount(), 0,
                "Shopping Cart Badge is displayed incorrectly. Should be 1.");
        softAssert.assertAll();
    }


}
