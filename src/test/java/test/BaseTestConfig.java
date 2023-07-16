package test;

import org.apache.log4j.xml.DOMConfigurator;
import org.example.drivers.DriverContainer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import static org.example.drivers.DriverContainer.LOGGER;


//@Listeners({TestListener.class})
public class BaseTestConfig {
    WebDriver driver;

    @BeforeMethod
    public void initTest() {
        LOGGER.debug("Start test.");
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
    }
}
