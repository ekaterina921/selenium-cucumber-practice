package test;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject_model_yandex_disk.*;
import java.util.concurrent.TimeUnit;



public class YandexDiskTests implements Credentials {


    @Test
    public void testRestoringFromBin(){
        BaseTestConfig baseTestConfig = new BaseTestConfig();
        WebDriver driver =  baseTestConfig.initTest();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LandingPage landingPage = new LandingPage();
        landingPage.getDiskLandingPage(driver);
        SignInPage signInPage = landingPage.clickSignInButton(driver);
        ClientDiskPage clientDiskPage = signInPage.signIn(driver, USERNAME_YANDEX, PASSWORD_YANDEX);
        clientDiskPage.moveFileToBin(driver);
        TrashBinPage trashBinPage = clientDiskPage.openBin(driver);
        int elementsBeforeRestore = trashBinPage.countFiles(driver);
        trashBinPage.openFileContextMenu(driver);
        trashBinPage.clickContextMenuItem(driver, "div[data-key='item-0'] > span.Menu-Text");
        int elementsAfterRestore = trashBinPage.countFiles(driver);
        Assert.assertEquals(elementsAfterRestore, elementsBeforeRestore - 1);
        baseTestConfig.endTest();
    }
}
