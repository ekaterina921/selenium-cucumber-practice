package test;

import drivers.DriverContainer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestConfig {
    @BeforeMethod
    public WebDriver initTest() {
        DriverContainer.getDriver().manage().window().maximize();
        return DriverContainer.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
