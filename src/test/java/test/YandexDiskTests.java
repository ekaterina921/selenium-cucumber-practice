package test;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject_model_yandex_disk.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class YandexDiskTests implements ConstantsSauceDemo {


    @Test
    public void testRestoringFromBin() throws MalformedURLException {
      DesiredCapabilities caps = new DesiredCapabilities();
       caps.setPlatform(Platform.WIN10);
       WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LandingPage landingPage = new LandingPage();
        landingPage.getDiskLandingPage(driver);
        SignInPage signInPage = landingPage.clickSignInButton(driver);
        ClientDiskPage clientDiskPage = signInPage.signIn(driver);
        clientDiskPage.moveFileToBin(driver);
        TrashBinPage trashBinPage = clientDiskPage.openBin(driver);
        int elementsBeforeRestore = trashBinPage.countFiles(driver);
        trashBinPage.openFileContextMenu(driver);
        trashBinPage.clickContextMenuItem(driver, "div[data-key='item-0'] > span.Menu-Text");
        int elementsAfterRestore = trashBinPage.countFiles(driver);
        Assert.assertEquals(elementsAfterRestore, elementsBeforeRestore - 1);
        driver.quit();
    }
}
