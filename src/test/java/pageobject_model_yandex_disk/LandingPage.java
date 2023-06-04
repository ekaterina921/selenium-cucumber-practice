package pageobject_model_yandex_disk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends YandexDiskPages {
    String landingURL = "https://360.yandex.ru/disk/";
    public void getDiskLandingPage(WebDriver driver) {
        driver.get(landingURL);
    }

    public SignInPage clickSignInButton(WebDriver driver) {
        driver.findElement(By.cssSelector(".Button2_view_default")).click();
        return new SignInPage();
    }
}
