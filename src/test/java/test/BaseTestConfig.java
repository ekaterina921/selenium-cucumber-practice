package test;

import lombok.extern.log4j.Log4j;
import org.example.drivers.DriverContainer;
import org.example.service.UserCreator;
import org.example.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Log4j
@Listeners(TestListener.class)
public class BaseTestConfig {
    WebDriver driver;

    @BeforeMethod
    public void initTest() {
        log.debug("Start test");
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        log.debug("End test");
        UserCreator.removeUser();
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
