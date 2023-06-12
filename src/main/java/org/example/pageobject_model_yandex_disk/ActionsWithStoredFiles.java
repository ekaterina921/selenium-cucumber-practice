package org.example.pageobject_model_yandex_disk;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public interface ActionsWithStoredFiles {
    default WebElement findFile(WebDriver driver) {
        return driver.findElement(By.cssSelector(YandexDiskPages.selectorForFiles));
    }

    default int countFiles(WebDriver driver) {
        return driver.findElements(By.cssSelector(YandexDiskPages.selectorForFiles)).size();
    }

    default void openFileContextMenu(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.contextClick(this.findFile(driver)).build().perform();
    }

    default void clickContextMenuItem (WebDriver driver, String CSSSelector) {
        String querySelector = "document.querySelector(\"" + CSSSelector + "\").click()";
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript(querySelector);
    }
}
