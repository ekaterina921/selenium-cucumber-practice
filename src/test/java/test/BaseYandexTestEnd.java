package test;

import org.example.drivers.DriverContainer;
import org.example.service.YandexUserCreator;
import org.example.utils.ExtendedListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;


@Listeners({ ExtendedListener.class})
public interface BaseYandexTestEnd {

    @AfterMethod(alwaysRun = true)
    public default void endTest() {
        new YandexUserCreator().removeUser();
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
