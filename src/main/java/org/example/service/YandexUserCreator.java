package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.YandexUser;

import static org.example.drivers.DriverContainer.LOGGER;

public class YandexUserCreator extends UserCreator {
    static ThreadLocal<YandexUser> local = new ThreadLocal<>();
    public static final String TESTDATA_YANDEX_USER_NAME = "testdata.yandex.user.name";
    public static final String TESTDATA_YANDEX_USER_PASSWORD = "testdata.yandex.user.password";

    @Override
    public YandexUser withCredentialsFromProperty() {
        if (local.get() == null) {
            LOGGER.debug("New Yandex User created.");
            local.set(new YandexUser(new TestDataReaderForUser(new TestDataReaderDecorator(new TestDataReader())).getTestData(TESTDATA_YANDEX_USER_NAME, "usernameYandex"),
                    new TestDataReaderForUser(new TestDataReaderDecorator(new TestDataReader())).getTestData(TESTDATA_YANDEX_USER_PASSWORD, "passwordYandex")));
        }
        return local.get();
    }
    @Override
    public void removeUser() {
        if (local.get() != null) {
            LOGGER.debug("Yandex User removed.");
            local.remove();
        }
    }
}
