package org.example.pageobject_model_sauce_demo;

import org.example.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends SauceDemoPages {
    protected WebDriver driver;
    private final By usernameBy = By.name("user-name");
    private final By passwordBy = By.name("password");
    private final By signinBy = By.name("login-button");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        checkCurrentPage(driver, "login_container", "Sign In");
    }

    public ProductsPage signIn(User user) {
        driver.findElement(usernameBy).sendKeys(user.getUsername());
        driver.findElement(passwordBy).sendKeys(user.getPassword());
        driver.findElement(signinBy).click();
        ExplicitWaits.waitPage(driver, "Products");
        return new ProductsPage(driver);
    }
}
