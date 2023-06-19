package org.example.pageobject_model_yandex_disk;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j
public class LandingPage extends YandexDiskPages {
    String landingURL = "https://360.yandex.ru/disk/";
    public void getDiskLandingPage(WebDriver driver) {
        driver.get(landingURL);
    }

    public SignInPage clickSignInButton(WebDriver driver) {
        driver.findElement(By.cssSelector(".Button2_view_default")).click();
        log.debug("Sign In page opening is triggered.");
        return new SignInPage();
    }
}
