package org.example.pageobject_model_sauce_demo;

import lombok.extern.log4j.Log4j;
import org.example.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j
public class SignInPage extends SauceDemoPages {
    protected WebDriver driver;
    private final By usernameInput = By.name("user-name");
    private final By passwordInput = By.name("password");
    private final By signInButton = By.name("login-button");
    static final String PRODUCTS_PAGE = "Products";

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        checkCurrentPage(driver, "login_container", "Swag Labs", "Sign In");
    }

    public ProductsPage signIn(User user) {
        driver.findElement(usernameInput).sendKeys(user.getUsername());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(signInButton).click();
        log.debug(String.format("%s opening triggered.", PRODUCTS_PAGE));
        ExplicitWaits.waitPage(driver, PRODUCTS_PAGE);
        return new ProductsPage(driver);
    }
}
