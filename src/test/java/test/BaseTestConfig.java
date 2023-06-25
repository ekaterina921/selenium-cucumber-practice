package test;

import lombok.extern.log4j.Log4j;
import org.example.drivers.DriverContainer;
import org.example.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Log4j
@Listeners(TestListener.class)
public class BaseTestConfig {
    WebDriver driver;

    @BeforeMethod
    public void initTest() {
        log.debug("Start test.");
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
    }
}
