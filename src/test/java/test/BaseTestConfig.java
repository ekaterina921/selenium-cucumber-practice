package test;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import lombok.extern.log4j.Log4j2;
import org.example.drivers.DriverContainer;
import org.example.utils.ExtendedListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static org.example.drivers.DriverContainer.LOGGER;

@Log4j2
@Listeners({ ExtendedListener.class})
public class BaseTestConfig {
    WebDriver driver;

    @BeforeMethod
    public void initTest() throws IOException {
        LOGGER.debug("Start test.");
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
    }
}
