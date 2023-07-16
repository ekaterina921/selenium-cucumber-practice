package org.example.service;

import lombok.extern.log4j.Log4j;


public class TestDataReaderDecorator implements TestDataReaderMethods {

    protected TestDataReaderMethods testDataReaderDecorator;

    public TestDataReaderDecorator(TestDataReaderMethods testDataReaderDecorator) {
        this.testDataReaderDecorator = testDataReaderDecorator;
    }

    @Override
    public String getTestData(String key){
        return testDataReaderDecorator.getTestData(key);
    }

    public String getTestData(String constant, String nameOrPassword) {
        return resourceBundle.getString(constant);
    }
}
