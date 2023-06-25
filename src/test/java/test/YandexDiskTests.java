package test;

import org.example.models.YandexUser;
import org.example.pageobject_model_yandex_disk.ClientDiskPage;
import org.example.pageobject_model_yandex_disk.LandingPage;
import org.example.pageobject_model_yandex_disk.SignInPage;
import org.example.pageobject_model_yandex_disk.TrashBinPage;
import org.example.service.YandexUserCreator;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;



public class YandexDiskTests extends BaseTestConfig implements Credentials, BaseYandexTestEnd {


    @Test
    public void testRestoringFromBin(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LandingPage landingPage = new LandingPage();
        landingPage.getDiskLandingPage(driver);
        SignInPage signInPage = landingPage.clickSignInButton(driver);
        YandexUser testUser = new YandexUserCreator().withCredentialsFromProperty();
        ClientDiskPage clientDiskPage = signInPage.signIn(driver, testUser);
        clientDiskPage.moveFileToBin(driver);
        TrashBinPage trashBinPage = clientDiskPage.openBin(driver);
        int elementsBeforeRestore = trashBinPage.countFiles(driver);
        trashBinPage.openFileContextMenu(driver);
        trashBinPage.clickContextMenuItem(driver, "div[data-key='item-0'] > span.Menu-Text");
        int elementsAfterRestore = trashBinPage.countFiles(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(elementsAfterRestore, elementsBeforeRestore - 1);
    }
}
