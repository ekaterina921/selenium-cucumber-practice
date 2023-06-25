package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.SauceUser;
import org.example.models.User;

@Log4j
public class SauceUserCreator extends UserCreator{

    public static final String TESTDATA_SAUCE_USER_NAME = "testdata.sauce.user.name";
    public static final String TESTDATA_SAUCE_USER_PASSWORD = "testdata.sauce.user.password";

    @Override
    public User withCredentialsFromProperty() {
        if (local.get() == null) {
            log.debug("New Sauce Demo User created.");
            local.set (new SauceUser(TestDataReader.getTestDataForUser(TESTDATA_SAUCE_USER_NAME,"usernameSauce"),
                    TestDataReader.getTestDataForUser(TESTDATA_SAUCE_USER_PASSWORD, "passwordSauce")));
        }
        return local.get();
    }
    }
