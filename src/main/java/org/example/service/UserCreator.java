package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.User;

@Log4j
public class UserCreator {
    static ThreadLocal<User> local = new ThreadLocal<>();
    public static final String TESTDATA_SAUCE_USER_NAME = "testdata.sauce.user.name";
    public static final String TESTDATA_SAUCE_USER_PASSWORD = "testdata.sauce.user.password";

    public static final String TESTDATA_YANDEX_USER_NAME = "testdata.yandex.user.name";
    public static final String TESTDATA_YANDEX_USER_PASSWORD = "testdata.yandex.user.password";

    public static User withCredentialsFromProperty(String website){

        if(local.get() == null && website.equalsIgnoreCase("yandex")) {
            log.debug("New User created.");
        local.set (new User(TestDataReader.getTestData(TESTDATA_YANDEX_USER_NAME),
                TestDataReader.getTestData(TESTDATA_YANDEX_USER_PASSWORD)));

        } else if (local.get() == null) {
            log.debug("New User created.");
            local.set (new User(TestDataReader.getTestData(TESTDATA_SAUCE_USER_NAME),
                    TestDataReader.getTestData(TESTDATA_SAUCE_USER_PASSWORD)));
        }
        return local.get();
    }

    public static void removeUser() {
        if (local.get() != null) {
            log.debug("User removed.");
            local.remove();
        }
    }
}
