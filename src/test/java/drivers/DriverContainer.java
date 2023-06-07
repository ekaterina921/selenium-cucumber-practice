package drivers;
import org.openqa.selenium.WebDriver;


public class DriverContainer {
    static ThreadLocal<WebDriver> local = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (local.get() == null) {
            local.set(RemoteDriverCreator.createDriver());
        }
        return local.get();
    }

    public static void removeDriver() {
        if (local.get() != null) {
            local.remove();
        }
    }

}
