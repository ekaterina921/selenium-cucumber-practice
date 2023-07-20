package org.example.pageobject_model_yandex_disk;


import com.epam.reportportal.testng.ReportPortalTestNGListener;
import lombok.extern.log4j.Log4j2;
import org.example.utils.ExtendedListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({ExtendedListener.class, ReportPortalTestNGListener.class})
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
