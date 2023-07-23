package org.example.pageobject_model_yandex_disk;

import lombok.extern.log4j.Log4j2;
import org.example.models.YandexUser;
import org.example.utils.ExtendedListener;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({ExtendedListener.class})
public class SignInPage extends YandexDiskPages {

    static final String SIGN_IN_BUTTON_ID = "passp:sign-in";
    public ClientDiskPage signIn(WebDriver driver, YandexUser yandexUser) {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.id("passp-field-login"))).sendKeys(yandexUser.getUsername()).sendKeys(Keys.RETURN).build().perform();
        WebElement nextButton = driver.findElement(By.id(SIGN_IN_BUTTON_ID));
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].click();", nextButton);
        driver.findElement(By.id("passp-field-passwd")).sendKeys(yandexUser.getPassword());
        driver.findElement(By.id(SIGN_IN_BUTTON_ID)).click();
        log.debug ("Sign in is finished. ClientDiskPage opening is triggered.");
        return new ClientDiskPage();
    }
}
