package pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    public void signIn(String userName, String password) {
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(signinBy).click();
        ExplicitWaits.waitPage(driver, "Products");
    }
}
