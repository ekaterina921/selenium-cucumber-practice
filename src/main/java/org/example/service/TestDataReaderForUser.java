package org.example.service;

import lombok.extern.log4j.Log4j;

import static org.example.drivers.DriverContainer.LOGGER;


public class TestDataReaderForUser extends TestDataReaderDecorator {
    public TestDataReaderForUser(TestDataReaderMethods testDataReaderDecorator) {
        super(testDataReaderDecorator);
    }

    @Override
    public String getTestData(String constant, String nameOrPassword) {
        if (testDataReaderDecorator.getTestData(constant).isBlank()) {
            return System.getProperty(nameOrPassword);
        } else {
            LOGGER.warn(String.format("Default test data for %s will be used. " +
                    "It may cause an issue as test credentials are not stored in the shared repository.", nameOrPassword));
            return testDataReaderDecorator.getTestData(constant);
        }
    }
}