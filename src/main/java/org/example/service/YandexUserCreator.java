package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.User;
import org.example.models.YandexUser;

@Log4j
public class YandexUserCreator extends UserCreator {
    public static final String TESTDATA_YANDEX_USER_NAME = "testdata.yandex.user.name";
    public static final String TESTDATA_YANDEX_USER_PASSWORD = "testdata.yandex.user.password";

    @Override
    public User withCredentialsFromProperty() {
        if (local.get() == null) {
            log.debug("New Yandex User created.");
            local.set(new YandexUser(TestDataReader.getTestDataForUser(TESTDATA_YANDEX_USER_NAME, "usernameYandex"),
                    TestDataReader.getTestDataForUser(TESTDATA_YANDEX_USER_PASSWORD, "passwordYandex")));
        }
        return local.get();
    }
}
