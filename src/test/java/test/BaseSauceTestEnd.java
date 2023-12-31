package test;


import org.example.drivers.DriverContainer;
import org.example.service.SauceUserCreator;
import org.example.utils.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public interface BaseSauceTestEnd {

    @AfterMethod(alwaysRun = true)
    default void endTest() {
        new SauceUserCreator().removeUser();
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
