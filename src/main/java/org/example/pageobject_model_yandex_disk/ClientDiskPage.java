package org.example.pageobject_model_yandex_disk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClientDiskPage extends YandexDiskPages implements ActionsWithStoredFiles {
    public  WebElement findBin(WebDriver driver) {
        return driver.findElement(By.cssSelector("#app .listing-item__icon.listing-item__icon_type_icon.listing-item__icon_resource_dir span"));
    }

    public void moveFileToBin(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(this.findFile(driver), this.findBin(driver)).build().perform();
    }

    public TrashBinPage openBin(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.findBin(driver)).doubleClick().build().perform();
        return new TrashBinPage();
    }
}
