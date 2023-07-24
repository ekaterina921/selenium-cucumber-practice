package org.example.pageobject_model_yandex_disk;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class ClientDiskPage extends YandexDiskPages implements ActionsWithStoredFiles {
    public static final String TRASH_BIN = "Trash bin";

    public  WebElement findBin(WebDriver driver) {
        log.debug(String.format("Searching for %s.", TRASH_BIN));
        return driver.findElement(By.cssSelector("#app .listing-item__icon.listing-item__icon_type_icon.listing-item__icon_resource_dir span"));
    }

    public void moveFileToBin(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(this.findFile(driver), this.findBin(driver)).build().perform();
        log.debug(String.format("Moving files to %s.", TRASH_BIN));
    }

    public TrashBinPage openBin(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.findBin(driver)).doubleClick().build().perform();
        log.debug(String.format("%s opening is triggered.", TRASH_BIN));
        return new TrashBinPage();
    }
}
