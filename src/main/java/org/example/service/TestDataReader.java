package org.example.service;

import lombok.extern.log4j.Log4j;

import java.util.ResourceBundle;
@Log4j
public class TestDataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key){
        return resourceBundle.getString(key);
    }

    public static String getTestDataForUser(String constant, String nameOrPassword) {
        if(TestDataReader.getTestData(constant).isBlank()) {
            return System.getProperty(nameOrPassword);
        } else {
            log.warn (String.format("Default test data for %s will be used. " +
                    "It may cause an issue as test credentials are not stored in the shared repository.", nameOrPassword));
            return TestDataReader.getTestData(constant);
        }
    }
}