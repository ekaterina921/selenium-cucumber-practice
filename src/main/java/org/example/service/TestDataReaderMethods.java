package org.example.service;

import java.util.ResourceBundle;

public interface TestDataReaderMethods {
    static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));
    abstract String getTestData(String key);
}
