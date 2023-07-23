package test;


import org.example.drivers.DriverContainer;
import org.example.service.SauceUserCreator;
import org.example.utils.ExtendedListener;
import org.example.utils.LoggingUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;


@Listeners({ ExtendedListener.class})
public interface BaseSauceTestEnd {
    DriverContainer driverContainer = new DriverContainer();
    @AfterMethod(alwaysRun = true)
    default void endTest(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            if (driverContainer.getDriver() instanceof TakesScreenshot) {
                String screenshot = ((TakesScreenshot) driverContainer.getDriver()).getScreenshotAs(OutputType.BASE64);
                LoggingUtils.logBase64(screenshot, "Invalid page");
            }
        }
        new SauceUserCreator().removeUser();
        driverContainer.getDriver().quit();
        driverContainer.removeDriver();
    }

}
