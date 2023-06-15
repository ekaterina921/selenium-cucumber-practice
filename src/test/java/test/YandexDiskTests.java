package test;

import org.example.drivers.DriverContainer;
import org.example.models.User;
import org.example.pageobject_model_yandex_disk.ClientDiskPage;
import org.example.pageobject_model_yandex_disk.LandingPage;
import org.example.pageobject_model_yandex_disk.SignInPage;
import org.example.pageobject_model_yandex_disk.TrashBinPage;
import org.example.service.UserCreator;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;



public class YandexDiskTests extends BaseTestConfig implements Credentials {


    @Test
    public void testRestoringFromBin(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LandingPage landingPage = new LandingPage();
        landingPage.getDiskLandingPage(driver);
        SignInPage signInPage = landingPage.clickSignInButton(driver);
        User testUser = UserCreator.withCredentialsFromProperty("yandex");
        ClientDiskPage clientDiskPage = signInPage.signIn(driver, testUser);
        clientDiskPage.moveFileToBin(driver);
        TrashBinPage trashBinPage = clientDiskPage.openBin(driver);
        int elementsBeforeRestore = trashBinPage.countFiles(driver);
        trashBinPage.openFileContextMenu(driver);
        trashBinPage.clickContextMenuItem(driver, "div[data-key='item-0'] > span.Menu-Text");
        int elementsAfterRestore = trashBinPage.countFiles(driver);
        Assert.assertEquals(elementsAfterRestore, elementsBeforeRestore - 1);
    }
}
