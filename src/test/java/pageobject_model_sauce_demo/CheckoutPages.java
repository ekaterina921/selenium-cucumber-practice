package pageobject_model_sauce_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutPages extends SauceDemoPages {
    protected WebDriver driver;
    private final By userFirstNameBy = By.name("firstName");
    private final By userLastNameBy = By.name("lastName");
    private final By postalCodeBy = By.name("postalCode");
    private final By continueBy = By.name("continue");

    CheckoutPages(WebDriver driver) {
        this.driver = driver;
    }

    public void fillAndSubmitCheckoutForm(String userFirstName, String userLastName, String postalCode) {
        fillCheckoutForm(userFirstName, userLastName, postalCode);
        submitCheckoutForm();
        ExplicitWaits.waitPage(driver, "Checkout: Overview");
    }

    private void fillCheckoutForm(String userFirstName, String userLastName, String postalCode) {
        driver.findElement(userFirstNameBy).sendKeys(userFirstName);
        driver.findElement(userLastNameBy).sendKeys(userLastName);
        driver.findElement(postalCodeBy).sendKeys(postalCode);
    }

    private void submitCheckoutForm() {
        driver.findElement(continueBy).click();
    }

    public void finishCheckout() {
        try {
            driver.findElement(By.name("finish")).click();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("This is not the second Checkout Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
