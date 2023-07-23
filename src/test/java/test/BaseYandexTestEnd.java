package test;

import org.example.drivers.DriverContainer;
import org.example.service.YandexUserCreator;
import org.example.utils.ExtendedListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;


@Listeners({ ExtendedListener.class})
public interface BaseYandexTestEnd {
    DriverContainer driverContainer = new DriverContainer();
    @AfterMethod(alwaysRun = true)
    default void endTest() {
        new YandexUserCreator().removeUser();
        driverContainer.getDriver().quit();
        driverContainer.removeDriver();
    }
}
