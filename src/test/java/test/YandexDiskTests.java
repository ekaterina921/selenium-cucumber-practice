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
        DriverContainer.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LandingPage landingPage = new LandingPage();
        landingPage.getDiskLandingPage(DriverContainer.getDriver());
        SignInPage signInPage = landingPage.clickSignInButton(DriverContainer.getDriver());
        User testUser = UserCreator.withCredentialsFromProperty("yandex");
        ClientDiskPage clientDiskPage = signInPage.signIn(DriverContainer.getDriver(), testUser);
        clientDiskPage.moveFileToBin(DriverContainer.getDriver());
        TrashBinPage trashBinPage = clientDiskPage.openBin(DriverContainer.getDriver());
        int elementsBeforeRestore = trashBinPage.countFiles(DriverContainer.getDriver());
        trashBinPage.openFileContextMenu(DriverContainer.getDriver());
        trashBinPage.clickContextMenuItem(DriverContainer.getDriver(), "div[data-key='item-0'] > span.Menu-Text");
        int elementsAfterRestore = trashBinPage.countFiles(DriverContainer.getDriver());
        Assert.assertEquals(elementsAfterRestore, elementsBeforeRestore - 1);
    }
}
