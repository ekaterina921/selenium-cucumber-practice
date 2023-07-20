package test;


import org.example.drivers.DriverContainer;
import org.example.service.SauceUserCreator;
import org.example.utils.ExtendedListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;


@Listeners({ ExtendedListener.class})
public interface BaseSauceTestEnd {

    @AfterMethod(alwaysRun = true)
    default void endTest(ITestResult testResult) {
        new SauceUserCreator().removeUser();
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
