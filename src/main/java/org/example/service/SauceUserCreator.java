package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.SauceUser;

@Log4j
public class SauceUserCreator extends UserCreator{
    static ThreadLocal<SauceUser> local = new ThreadLocal<>();
    public static final String TESTDATA_SAUCE_USER_NAME = "testdata.sauce.user.name";
    public static final String TESTDATA_SAUCE_USER_PASSWORD = "testdata.sauce.user.password";

    @Override
    public SauceUser withCredentialsFromProperty() {
        if (local.get() == null) {
            log.debug("New Sauce Demo User created.");
            local.set (new SauceUser(TestDataReader.getTestDataForUser(TESTDATA_SAUCE_USER_NAME,"usernameSauce"),
                    TestDataReader.getTestDataForUser(TESTDATA_SAUCE_USER_PASSWORD, "passwordSauce")));
        }
        return local.get();
    }
    @Override
    public void removeUser() {
        if (local.get() != null) {
            log.debug("Sauce User removed.");
            local.remove();
        }
    }
    }
