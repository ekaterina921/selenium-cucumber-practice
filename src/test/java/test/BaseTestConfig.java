package test;

import lombok.extern.log4j.Log4j2;
import org.example.drivers.DriverContainer;
import org.example.utils.ExtendedListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Log4j2
@Listeners({ ExtendedListener.class})
public class BaseTestConfig {
    WebDriver driver;

    @BeforeMethod
    public void initTest(ITestContext context) {
        if (System.getProperty("environment") == null) {
            System.setProperty("environment", "qa" );
        }
        if (System.getProperty("browser") == null) {
            System.setProperty("browser", "chrome");
        }
        DriverContainer driverContainer = new DriverContainer();
        driver = driverContainer.getDriver();
        log.debug("Start test.");
        String variable = "driver";
        context.setAttribute(variable, driver);
        driver.manage().window().maximize();
    }
}
