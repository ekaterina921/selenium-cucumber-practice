package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j2;
import org.example.models.SauceUser;
import org.example.utils.ExplicitWaits;
import org.example.utils.ExtendedListener;
import org.example.utils.LoggingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({ExtendedListener.class})
public class SignInPage extends SauceDemoPages {
    protected WebDriver driver;
    private final By usernameInput = By.name("user-name");
    private final By passwordInput = By.name("password");
    private final By signInButton = By.name("login-button");
    static final String PRODUCTS_PAGE = "Products";

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        checkCurrentPage(driver, "login_logo", "Swag Labs", "Sign In");
    }

    public ProductsPage signIn(SauceUser user) {
        driver.findElement(usernameInput).sendKeys(user.getUsername());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(signInButton).click();
        LoggingUtils.logPlain(String.format("%s opening triggered.", PRODUCTS_PAGE));
        ExplicitWaits.waitPage(driver, PRODUCTS_PAGE);
        return new ProductsPage(driver);
    }
}
