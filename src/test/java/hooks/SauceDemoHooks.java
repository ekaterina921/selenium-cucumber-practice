//package hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import lombok.extern.log4j.Log4j;
//import org.example.drivers.DriverContainer;
//import org.example.service.SauceUserCreator;
//import org.example.utils.TestListener;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.Listeners;
//
//
//@Log4j
//@Listeners(TestListener.class)
//public class SauceDemoHooks {
//    WebDriver driver;
//
//    @Before
//    public void initTest(Scenario sc) {
//        System.setProperty("environment", "qa");
//        System.getProperty("browser", "chrome");
//        log.debug("Start test." + sc.getName());
//        driver = DriverContainer.getDriver();
//        driver.manage().window().maximize();
//    }
//
//    @After
//    public void endTest() {
//        new SauceUserCreator().removeUser();
//        DriverContainer.getDriver().quit();
//        DriverContainer.removeDriver();
//    }
//}
