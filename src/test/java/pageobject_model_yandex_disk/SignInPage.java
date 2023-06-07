package pageobject_model_yandex_disk;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class SignInPage extends YandexDiskPages {

    static final String SIGN_IN_BUTTON_ID = "passp:sign-in";
    public ClientDiskPage signIn(WebDriver driver, String username, String password) {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.id("passp-field-login"))).sendKeys(username).sendKeys(Keys.RETURN).build().perform();
        WebElement nextButton = driver.findElement(By.id(SIGN_IN_BUTTON_ID));
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].click();", nextButton);
        driver.findElement(By.id("passp-field-passwd")).sendKeys(password);
        driver.findElement(By.id(SIGN_IN_BUTTON_ID)).click();
        return new ClientDiskPage();
    }
}
